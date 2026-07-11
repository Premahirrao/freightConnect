package com.freightconnect.repository

import com.freightconnect.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class FreightRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val usersRef = db.collection("users")
    private val truckRoutesRef = db.collection("truckRoutes")
    private val cargoRequestsRef = db.collection("cargoRequests")
    private val interestsRef = db.collection("bookingInterests")
    private val fleetProfilesRef = db.collection("fleetProfiles")
    private val notificationsRef = db.collection("notifications")

    // ═══════════════════════════════════════════════════════════════════════
    // USER MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun createUser(user: User) {
        usersRef.document(user.uid).set(user).await()
    }

    suspend fun getUser(uid: String): User? =
        usersRef.document(uid).get().await().toObject(User::class.java)

    suspend fun updateUserLanguage(uid: String, languageCode: String) {
        usersRef.document(uid).update("preferredLanguage", languageCode).await()
    }

    suspend fun updateFCMToken(uid: String, token: String) {
        usersRef.document(uid).update("fcmToken", token).await()
    }

    fun currentUid(): String = auth.currentUser?.uid ?: ""

    // ═══════════════════════════════════════════════════════════════════════
    // TRUCK ROUTES (Posted by Fleet Owners)
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun postTruckRoute(route: TruckRoute): String {
        val ref = truckRoutesRef.document()
        val withId = route.copy(routeId = ref.id)
        ref.set(withId).await()
        return ref.id
    }

    suspend fun getFleetOwnerRoutes(uid: String): List<TruckRoute> =
        truckRoutesRef.whereEqualTo("fleetOwnerUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
            .toObjects(TruckRoute::class.java)

    suspend fun getActiveRoutes(): List<TruckRoute> =
        truckRoutesRef.whereEqualTo("status", RouteStatus.ACTIVE.name)
            .orderBy("departureDate", Query.Direction.ASCENDING)
            .get().await()
            .toObjects(TruckRoute::class.java)

    suspend fun searchRoutesByCity(fromCity: String, toCity: String): List<TruckRoute> {
        val query = truckRoutesRef
            .whereEqualTo("status", RouteStatus.ACTIVE.name)
            
        return if (fromCity.isNotBlank() && toCity.isNotBlank()) {
            query.whereEqualTo("fromCity", fromCity)
                .whereEqualTo("toCity", toCity)
                .get().await()
                .toObjects(TruckRoute::class.java)
        } else if (fromCity.isNotBlank()) {
            query.whereEqualTo("fromCity", fromCity)
                .get().await()
                .toObjects(TruckRoute::class.java)
        } else if (toCity.isNotBlank()) {
            query.whereEqualTo("toCity", toCity)
                .get().await()
                .toObjects(TruckRoute::class.java)
        } else {
            getActiveRoutes()
        }
    }

    /**
     * Listen to active routes matching optional from/to city filters in real-time.
     * Returns a ListenerRegistration which should be removed by the caller when no longer needed.
     */
    fun listenToActiveRoutes(
        fromCity: String = "",
        toCity: String = "",
        onUpdate: (List<TruckRoute>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration {
        var query: Query = truckRoutesRef.whereEqualTo("status", RouteStatus.ACTIVE.name)

        if (fromCity.isNotBlank()) query = query.whereEqualTo("fromCity", fromCity)
        if (toCity.isNotBlank()) query = query.whereEqualTo("toCity", toCity)

        // Order by departure date for a stable list
        query = query.orderBy("departureDate", Query.Direction.ASCENDING)

        return query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val routes = snapshot.toObjects(TruckRoute::class.java)
                onUpdate(routes)
            }
        }
    }

    suspend fun getTruckRoute(routeId: String): TruckRoute? =
        truckRoutesRef.document(routeId).get().await()
            .toObject(TruckRoute::class.java)

    suspend fun updateRouteStatus(routeId: String, status: RouteStatus) {
        truckRoutesRef.document(routeId).update("status", status.name).await()
    }

    suspend fun cancelRoute(routeId: String) {
        val route = truckRoutesRef.document(routeId).get().await()
            .toObject(TruckRoute::class.java) ?: return
        if (route.status !in listOf(RouteStatus.ACTIVE, RouteStatus.PARTIALLY_BOOKED)) return

        val pendingDocs = interestsRef.whereEqualTo("routeId", routeId)
            .whereEqualTo("status", InterestStatus.PENDING.name)
            .get().await().documents
        val pendingInterests = pendingDocs.mapNotNull { it.toObject(BookingInterest::class.java) }

        db.runTransaction { transaction ->
            transaction.update(
                truckRoutesRef.document(routeId),
                "status",
                RouteStatus.CANCELLED.name
            )
            pendingDocs.forEach { doc ->
                transaction.update(
                    doc.reference,
                    mapOf(
                        "status" to InterestStatus.REJECTED.name,
                        "rejectionReason" to "Cancelled by owner"
                    )
                )
            }
        }.await()

        pendingInterests.forEach { interest ->
            val initiator = getUser(interest.initiatorUid)
            notifyInterestCancelled(interest, initiator, "route")
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // CARGO REQUESTS (Posted by Business Owners)
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun postCargoRequest(cargo: CargoRequest): String {
        val ref = cargoRequestsRef.document()
        val withId = cargo.copy(cargoId = ref.id)
        ref.set(withId).await()
        return ref.id
    }

    suspend fun getBusinessOwnerCargos(uid: String): List<CargoRequest> =
        cargoRequestsRef.whereEqualTo("businessOwnerUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
            .toObjects(CargoRequest::class.java)

    suspend fun getOpenCargos(): List<CargoRequest> =
        cargoRequestsRef.whereEqualTo("status", CargoStatus.OPEN.name)
            .orderBy("pickupDate", Query.Direction.ASCENDING)
            .get().await()
            .toObjects(CargoRequest::class.java)

    suspend fun searchCargosByCity(fromCity: String, toCity: String): List<CargoRequest> {
        val query = cargoRequestsRef
            .whereEqualTo("status", CargoStatus.OPEN.name)

        return if (fromCity.isNotBlank() && toCity.isNotBlank()) {
            query.whereEqualTo("pickupCity", fromCity)
                .whereEqualTo("deliveryCity", toCity)
                .get().await()
                .toObjects(CargoRequest::class.java)
        } else if (fromCity.isNotBlank()) {
            query.whereEqualTo("pickupCity", fromCity)
                .get().await()
                .toObjects(CargoRequest::class.java)
        } else if (toCity.isNotBlank()) {
            query.whereEqualTo("deliveryCity", toCity)
                .get().await()
                .toObjects(CargoRequest::class.java)
        } else {
            getOpenCargos()
        }
    }

    suspend fun getCargoRequest(cargoId: String): CargoRequest? =
        cargoRequestsRef.document(cargoId).get().await()
            .toObject(CargoRequest::class.java)

    suspend fun updateCargoStatus(cargoId: String, status: CargoStatus) {
        cargoRequestsRef.document(cargoId).update("status", status.name).await()
    }

    suspend fun cancelCargo(cargoId: String) {
        val cargo = cargoRequestsRef.document(cargoId).get().await()
            .toObject(CargoRequest::class.java) ?: return
        if (cargo.status !in listOf(CargoStatus.OPEN, CargoStatus.PENDING)) return

        val pendingDocs = interestsRef.whereEqualTo("cargoId", cargoId)
            .whereEqualTo("status", InterestStatus.PENDING.name)
            .get().await().documents
        val pendingInterests = pendingDocs.mapNotNull { it.toObject(BookingInterest::class.java) }

        db.runTransaction { transaction ->
            transaction.update(
                cargoRequestsRef.document(cargoId),
                "status",
                CargoStatus.CANCELLED.name
            )
            pendingDocs.forEach { doc ->
                transaction.update(
                    doc.reference,
                    mapOf(
                        "status" to InterestStatus.REJECTED.name,
                        "rejectionReason" to "Cancelled by owner"
                    )
                )
            }
        }.await()

        pendingInterests.forEach { interest ->
            val initiator = getUser(interest.initiatorUid)
            notifyInterestCancelled(interest, initiator, "cargo")
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // BOOKING INTERESTS
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun sendInterest(interest: BookingInterest): String {
        if (interest.routeId.isNotBlank()) {
            val route = getTruckRoute(interest.routeId)
            val routeIsBookable = route != null &&
                route.status in listOf(RouteStatus.ACTIVE, RouteStatus.PARTIALLY_BOOKED) &&
                route.remainingCapacityTons > 0f
            if (!routeIsBookable) {
                throw IllegalStateException("Route is not available for new interests")
            }
        }
        if (interest.cargoId.isNotBlank()) {
            val cargo = getCargoRequest(interest.cargoId)
            val cargoIsBookable = cargo != null &&
                cargo.status in listOf(CargoStatus.OPEN, CargoStatus.PENDING)
            if (!cargoIsBookable) {
                throw IllegalStateException("Cargo is not available for new interests")
            }
        }
        val ref = interestsRef.document()
        val withId = interest.copy(interestId = ref.id)
        ref.set(withId).await()
        
        // Update cargo/route status
        if (interest.cargoId.isNotBlank()) {
            updateCargoStatus(interest.cargoId, CargoStatus.PENDING)
        }
        
        return ref.id
    }

    suspend fun getReceivedInterests(uid: String): List<BookingInterest> =
        interestsRef.whereEqualTo("targetUid", uid)
            .whereEqualTo("status", InterestStatus.PENDING.name)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
            .toObjects(BookingInterest::class.java)

    suspend fun getSentInterests(uid: String): List<BookingInterest> =
        interestsRef.whereEqualTo("initiatorUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
            .toObjects(BookingInterest::class.java)

//    suspend fun acceptInterest(interestId: String) {
//        val interest = interestsRef.document(interestId).get().await()
//            .toObject(BookingInterest::class.java) ?: return
//
//        db.runTransaction { transaction ->
//            // Accept this interest
//            transaction.update(
//                interestsRef.document(interestId),
//                "status", InterestStatus.ACCEPTED.name
//            )
//
//            // Update cargo/route status
//            if (interest.cargoId.isNotBlank()) {
//                transaction.update(
//                    cargoRequestsRef.document(interest.cargoId),
//                    mapOf(
//                        "status" to CargoStatus.BOOKED.name,
//                        "assignedFleetOwnerUid" to interest.initiatorUid,
//                        "assignedRouteId" to interest.routeId
//                    )
//                )
//            }
//
//            if (interest.routeId.isNotBlank()) {
//                transaction.update(
//                    truckRoutesRef.document(interest.routeId),
//                    mapOf(
//                        "status" to RouteStatus.BOOKED.name,
//                        "bookedByUid" to interest.initiatorUid
//                    )
//                )
//            }
//
//            // Reject all other pending interests for the same cargo/route
//            val query = if (interest.cargoId.isNotBlank()) {
//                interestsRef.whereEqualTo("cargoId", interest.cargoId)
//            } else {
//                interestsRef.whereEqualTo("routeId", interest.routeId)
//            }
//
//            query.whereEqualTo("status", InterestStatus.PENDING.name)
//                .get().await()
//                .documents.forEach { doc ->
//                    if (doc.id != interestId) {
//                        transaction.update(doc.reference, "status", InterestStatus.REJECTED.name)
//                    }
//                }
//        }.await()
suspend fun acceptInterest(interestId: String) {

    val interest = interestsRef.document(interestId)
        .get()
        .await()
        .toObject(BookingInterest::class.java) ?: return

    if (interest.status != InterestStatus.PENDING) {
        throw IllegalStateException("Interest already processed")
    }
    if (interest.routeId.isBlank() && interest.cargoId.isBlank()) {
        throw IllegalStateException("Interest has no route or cargo")
    }

    suspend fun reject(reason: String): Nothing {
        interestsRef.document(interestId)
            .update(
                mapOf(
                    "status" to InterestStatus.REJECTED.name,
                    "rejectionReason" to reason
                )
            ).await()
        throw IllegalStateException(reason)
    }

    // Fetch cargo/route and pending interests before transaction
    val cargo = if (interest.cargoId.isNotBlank()) {
        cargoRequestsRef.document(interest.cargoId).get().await()
            .toObject(CargoRequest::class.java)
    } else null

    val route = if (interest.routeId.isNotBlank()) {
        truckRoutesRef.document(interest.routeId).get().await()
            .toObject(TruckRoute::class.java)
    } else null

    val query = if (interest.cargoId.isNotBlank()) {
        interestsRef.whereEqualTo("cargoId", interest.cargoId)
    } else {
        interestsRef.whereEqualTo("routeId", interest.routeId)
    }

    val pendingDocs = query
        .whereEqualTo("status", InterestStatus.PENDING.name)
        .get()
        .await()
        .documents

    val requestedWeight = when {
        interest.goodsWeightTons > 0f -> interest.goodsWeightTons
        cargo != null -> cargo.weightTons
        route != null -> route.remainingCapacityTons
        else -> 0f
    }

    if (interest.routeId.isNotBlank() && route != null) {
        if (requestedWeight <= 0f) {
            reject("Missing goods weight")
        }
        if (route.remainingCapacityTons <= 0f) {
            reject("Route is fully booked")
        }
        if (requestedWeight > route.remainingCapacityTons) {
            reject("Not enough remaining capacity")
        }
    }

    if (interest.cargoId.isNotBlank() && cargo != null) {
        if (cargo.status == CargoStatus.BOOKED || cargo.status == CargoStatus.CANCELLED) {
            reject("Cargo is no longer available")
        }
    }

    val newBookedWeight = if (route != null) route.bookedWeightTons + requestedWeight else 0f
    val totalCapacity = route?.availableCapacityTons ?: 0f
    val remainingCapacity = if (route != null) {
        (totalCapacity - newBookedWeight).coerceAtLeast(0f)
    } else {
        0f
    }
    val newRouteStatus = if (route != null) {
        when {
            remainingCapacity <= 0f -> RouteStatus.BOOKED
            remainingCapacity < totalCapacity -> RouteStatus.PARTIALLY_BOOKED
            else -> RouteStatus.ACTIVE
        }
    } else {
        null
    }

    db.runTransaction { transaction ->

        transaction.update(
            interestsRef.document(interestId),
            "status", InterestStatus.ACCEPTED.name
        )

        if (interest.cargoId.isNotBlank() && cargo != null) {
            transaction.update(
                cargoRequestsRef.document(interest.cargoId),
                mapOf(
                    "status" to CargoStatus.BOOKED.name,
                    "assignedFleetOwnerUid" to interest.initiatorUid,
                    "assignedRouteId" to interest.routeId
                )
            )
        }

        if (interest.routeId.isNotBlank() && route != null && newRouteStatus != null) {
            transaction.update(
                truckRoutesRef.document(interest.routeId),
                mapOf(
                    "bookedWeightTons" to newBookedWeight,
                    "status" to newRouteStatus.name,
                    "bookedByUid" to interest.initiatorUid
                )
            )
        }

        if (interest.cargoId.isNotBlank()) {
            pendingDocs.forEach { doc ->
                if (doc.id != interestId) {
                    transaction.update(
                        doc.reference,
                        "status",
                        InterestStatus.REJECTED.name
                    )
                }
            }
        }

        if (interest.routeId.isNotBlank() && newRouteStatus == RouteStatus.BOOKED) {
            pendingDocs.forEach { doc ->
                if (doc.id != interestId) {
                    transaction.update(
                        doc.reference,
                        "status",
                        InterestStatus.REJECTED.name
                    )
                }
            }
        }
    }.await()

    val initiator = getUser(interest.initiatorUid)
    notifyInterestAccepted(interest, initiator)
}

    suspend fun rejectInterest(interestId: String, reason: String = "") {
        // Task 10: Save rejection reason along with status
        interestsRef.document(interestId)
            .update(mapOf(
                "status" to InterestStatus.REJECTED.name,
                "rejectionReason" to reason
            )).await()
        
        // Task 12: Send notification to initiator that interest was rejected
        val interest = interestsRef.document(interestId).get().await()
            .toObject(BookingInterest::class.java)
        if (interest != null) {
            val initiator = getUser(interest.initiatorUid)
            notifyInterestRejected(interest, initiator)
        }
    }


    fun listenToFleetRoutes(
        uid: String,
        onUpdate: (List<TruckRoute>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        truckRoutesRef.whereEqualTo("fleetOwnerUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val routes = snapshot.toObjects(TruckRoute::class.java)
                    onUpdate(routes)
                }
            }

    fun listenToFleetInterests(
        uid: String,
        onUpdate: (List<BookingInterest>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        interestsRef.whereEqualTo("targetUid", uid)
            .whereEqualTo("status", InterestStatus.PENDING.name)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val interests = snapshot.toObjects(BookingInterest::class.java)
                    onUpdate(interests)
                }
            }

    fun listenToBusinessCargos(
        uid: String,
        onUpdate: (List<CargoRequest>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        cargoRequestsRef.whereEqualTo("businessOwnerUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val cargos = snapshot.toObjects(CargoRequest::class.java)
                    onUpdate(cargos)
                }
            }

    fun listenToBusinessInterests(
        uid: String,
        onUpdate: (List<BookingInterest>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        interestsRef.whereEqualTo("targetUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val interests = snapshot.toObjects(BookingInterest::class.java)
                    onUpdate(interests)
                }
            }

    suspend fun getFleetProfile(uid: String): FleetOwnerProfile? =
        fleetProfilesRef.document(uid).get().await()
            .toObject(FleetOwnerProfile::class.java)

    suspend fun createOrUpdateFleetProfile(profile: FleetOwnerProfile) {
        fleetProfilesRef.document(profile.uid).set(profile).await()
    }

    suspend fun updateFleetStats(
        uid: String,
        completedTrip: Boolean,
        onTime: Boolean,
        rating: Float
    ) {
        fleetProfilesRef.document(uid).update(
            mapOf(
                "totalTrips" to FieldValue.increment(1),
                "completedTrips" to if (completedTrip) FieldValue.increment(1) else FieldValue.increment(0),
                "onTimeDeliveries" to if (onTime) FieldValue.increment(1) else FieldValue.increment(0),
                "ratingsSum" to FieldValue.increment(rating.toDouble()),
                "ratingsCount" to FieldValue.increment(1)
            )
        ).await()
    }

    // ═══════════════════════════════════════════════════════════════════════
    // TASK 11: REAL-TIME LISTENERS FOR INTERESTS
    // ═══════════════════════════════════════════════════════════════════════

    /**
     * Task 11: Listen to received interests in real-time (replaces polling)
     * Emits updates whenever received interests change
     */
    fun listenToReceivedInterests(
        uid: String,
        onUpdate: (List<BookingInterest>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        interestsRef.whereEqualTo("targetUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val interests = snapshot.toObjects(BookingInterest::class.java)
                    onUpdate(interests)
                }
            }

    /**
     * Task 11: Listen to sent interests in real-time (replaces polling)
     * Emits updates whenever sent interests change
     */
    fun listenToSentInterests(
        uid: String,
        onUpdate: (List<BookingInterest>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration =
        interestsRef.whereEqualTo("initiatorUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val interests = snapshot.toObjects(BookingInterest::class.java)
                    onUpdate(interests)
                }
            }

    /**
     * Task 11: Get unread interest count (pending interests received)
     * Used for badge counter
     */
    suspend fun getUnreadInterestCount(uid: String): Int {
        return try {
            interestsRef.whereEqualTo("targetUid", uid)
                .whereEqualTo("status", InterestStatus.PENDING.name)
                .get().await()
                .size()
        } catch (e: Exception) {
            0
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // NOTIFICATIONS
    // ═══════════════════════════════════════════════════════════════════════

    /**
     * Task 12: Create and send a notification to user
     * Called when interest is received, accepted, rejected, etc
     */
    suspend fun sendNotification(
        recipientUid: String,
        type: NotificationType,
        title: String,
        body: String,
        relatedRouteId: String = "",
        relatedCargoId: String = "",
        relatedInterestId: String = ""
    ) {
        val notification = notifications(
            recipientUid = recipientUid,
            type = type,
            title = title,
            body = body,
            relatedRouteId = relatedRouteId,
            relatedCargoId = relatedCargoId,
            relatedInterestId = relatedInterestId
        )
        createNotification(notification)
    }

    /**
     * Task 12: Notify recipient when interest is received
     */
    suspend fun notifyInterestReceived(
        interest: BookingInterest,
        recipientUser: User?
    ) {
        if (recipientUser?.fcmToken?.isNotBlank() == true) {
            sendNotification(
                recipientUid = interest.targetUid,
                type = NotificationType.INTEREST_RECEIVED,
                title = "New interest from ${interest.initiatorName}",
                body = "${interest.initiatorRole.name.replace("_", " ")} is interested in your ${if (interest.cargoId.isNotBlank()) "cargo" else "route"}!",
                relatedInterestId = interest.interestId,
                relatedCargoId = interest.cargoId,
                relatedRouteId = interest.routeId
            )
        }
    }

    /**
     * Task 12: Notify initiator when interest is accepted
     */
    suspend fun notifyInterestAccepted(
        interest: BookingInterest,
        initiatorUser: User?
    ) {
        if (initiatorUser?.fcmToken?.isNotBlank() == true) {
            sendNotification(
                recipientUid = interest.initiatorUid,
                type = NotificationType.INTEREST_ACCEPTED,
                title = "Interest accepted!",
                body = "${interest.targetName} accepted your interest. You can now contact them!",
                relatedInterestId = interest.interestId,
                relatedCargoId = interest.cargoId,
                relatedRouteId = interest.routeId
            )
        }
    }

    /**
     * Task 12: Notify initiator when interest is rejected
     */
    suspend fun notifyInterestRejected(
        interest: BookingInterest,
        initiatorUser: User?
    ) {
        if (initiatorUser?.fcmToken?.isNotBlank() == true) {
            sendNotification(
                recipientUid = interest.initiatorUid,
                type = NotificationType.INTEREST_REJECTED,
                title = "Interest declined",
                body = "${interest.targetName} declined your interest${if (interest.rejectionReason.isNotBlank()) ": ${interest.rejectionReason}" else ""}",
                relatedInterestId = interest.interestId
            )
        }
    }

    private suspend fun notifyInterestCancelled(
        interest: BookingInterest,
        initiatorUser: User?,
        itemType: String
    ) {
        if (initiatorUser?.fcmToken?.isNotBlank() == true) {
            val title = if (itemType == "route") "Route cancelled" else "Cargo cancelled"
            val body = "The ${itemType} you were interested in was cancelled by the owner."
            sendNotification(
                recipientUid = interest.initiatorUid,
                type = NotificationType.CANCELLED,
                title = title,
                body = body,
                relatedInterestId = interest.interestId,
                relatedCargoId = interest.cargoId,
                relatedRouteId = interest.routeId
            )
        }
    }

    suspend fun createNotification(notification: notifications) {
        val ref = notificationsRef.document()
        notificationsRef.document(ref.id).set(notification.copy(id = ref.id)).await()
    }

    suspend fun getUserNotifications(uid: String): List<notifications> =
        notificationsRef.whereEqualTo("recipientUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(50)
            .get().await()
            .toObjects(notifications::class.java)

    suspend fun markNotificationAsRead(notificationId: String) {
        notificationsRef.document(notificationId).update("isRead", true).await()
    }
}
