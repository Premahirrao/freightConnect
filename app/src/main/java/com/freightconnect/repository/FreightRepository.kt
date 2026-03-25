package com.freightconnect.repository

import com.freightconnect.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
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

    suspend fun getTruckRoute(routeId: String): TruckRoute? =
        truckRoutesRef.document(routeId).get().await()
            .toObject(TruckRoute::class.java)

    suspend fun updateRouteStatus(routeId: String, status: RouteStatus) {
        truckRoutesRef.document(routeId).update("status", status.name).await()
    }

    suspend fun cancelRoute(routeId: String) {
        updateRouteStatus(routeId, RouteStatus.CANCELLED)
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
        updateCargoStatus(cargoId, CargoStatus.CANCELLED)
    }

    // ═══════════════════════════════════════════════════════════════════════
    // BOOKING INTERESTS
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun sendInterest(interest: BookingInterest): String {
        val ref = interestsRef.document()
        val withId = interest.copy(interestId = ref.id)
        ref.set(withId).await()
        
        // Update cargo/route status
        if (interest.cargoId.isNotBlank()) {
            updateCargoStatus(interest.cargoId, CargoStatus.PENDING)
        }
        if (interest.routeId.isNotBlank()) {
            updateRouteStatus(interest.routeId, RouteStatus.BOOKED)
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

    // ✅ STEP 1: Fetch pending interests BEFORE transaction
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

    // ✅ STEP 2: Run transaction (NO await inside)
    db.runTransaction { transaction ->

        // Accept selected
        transaction.update(
            interestsRef.document(interestId),
            "status", InterestStatus.ACCEPTED.name
        )

        // Update cargo
        if (interest.cargoId.isNotBlank()) {
            transaction.update(
                cargoRequestsRef.document(interest.cargoId),
                mapOf(
                    "status" to CargoStatus.BOOKED.name,
                    "assignedFleetOwnerUid" to interest.initiatorUid,
                    "assignedRouteId" to interest.routeId
                )
            )
        }

        // Update route
        if (interest.routeId.isNotBlank()) {
            transaction.update(
                truckRoutesRef.document(interest.routeId),
                mapOf(
                    "status" to RouteStatus.BOOKED.name,
                    "bookedByUid" to interest.initiatorUid
                )
            )
        }

        // Reject others
        pendingDocs.forEach { doc ->
            if (doc.id != interestId) {
                transaction.update(
                    doc.reference,
                    "status",
                    InterestStatus.REJECTED.name
                )
            }
        }
    }.await()
    }

    suspend fun rejectInterest(interestId: String) {
        interestsRef.document(interestId)
            .update("status", InterestStatus.REJECTED.name).await()
    }

    // ═══════════════════════════════════════════════════════════════════════
    // FLEET OWNER PROFILES
    // ═══════════════════════════════════════════════════════════════════════

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
    // NOTIFICATIONS
    // ═══════════════════════════════════════════════════════════════════════

    suspend fun createNotification(notification: AppNotification) {
        val ref = notificationsRef.document()
        notificationsRef.document(ref.id).set(notification.copy(id = ref.id)).await()
    }

    suspend fun getUserNotifications(uid: String): List<AppNotification> =
        notificationsRef.whereEqualTo("recipientUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(50)
            .get().await()
            .toObjects(AppNotification::class.java)

    suspend fun markNotificationAsRead(notificationId: String) {
        notificationsRef.document(notificationId).update("isRead", true).await()
    }
}
