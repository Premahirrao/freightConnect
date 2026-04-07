package com.freightconnect.model

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

// ═══════════════════════════════════════════════════════════════════════════
// USER & ROLES
// ═══════════════════════════════════════════════════════════════════════════

enum class UserRole {
    BUSINESS_OWNER,  // Posts cargo requests
    FLEET_OWNER      // Posts truck routes
}

data class User(
    val uid: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val role: UserRole = UserRole.BUSINESS_OWNER,
    val profileImageUrl: String = "",
    val companyName: String = "",
    val isVerified: Boolean = false,
    val preferredLanguage: String = "en", // en, hi, mr, te
    val fcmToken: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

// ═══════════════════════════════════════════════════════════════════════════
// VEHICLE TYPES
// ═══════════════════════════════════════════════════════════════════════════

enum class VehicleType(val displayNameEn: String, val maxCapacityTons: Float) {
    MINI_TRUCK("Mini Truck", 1.5f),
    TRUCK("Truck", 5f),
    HEAVY_TRUCK("Heavy Truck", 15f),
    TRAILER("Trailer / Container", 25f),
    PICKUP("Pickup Van", 0.8f),
    TANKER("Tanker", 20f),
    REFRIGERATED("Refrigerated Truck", 8f),
    FLATBED("Flatbed Truck", 12f)
}

// ═══════════════════════════════════════════════════════════════════════════
// FLEET OWNER (DRIVER) - Posts Available Routes
// ═══════════════════════════════════════════════════════════════════════════

enum class RouteStatus {
    ACTIVE,      // Route available for booking
    BOOKED,      // Someone booked it
    IN_TRANSIT,  // Currently transporting
    COMPLETED,   // Delivered
    CANCELLED
}

/**
 * Posted by FLEET_OWNER: "I have a truck going from X to Y, can carry Z tons"
 */
data class TruckRoute(
    val routeId: String = "",
    val fleetOwnerUid: String = "",
    val fleetOwnerName: String = "",
    val fleetOwnerPhone: String = "",
    val fleetOwnerCompany: String = "",
    
    // Route details
    val fromLocation: GeoPoint? = null,
    val fromAddress: String = "",
    val fromCity: String = "",
    val toLocation: GeoPoint? = null,
    val toAddress: String = "",
    val toCity: String = "",
    val estimatedDistanceKm: Float = 0f,
    
    // Vehicle details
    val vehicleType: VehicleType = VehicleType.TRUCK,
    val vehicleNumber: String = "",
    val availableCapacityTons: Float = 0f,
    val vehicleImageUrl: String = "",
    
    // Pricing & timing
    val pricePerTon: Double = 0.0,
    val currency: String = "INR",
    val departureDate: Long = 0L,
    val estimatedArrivalDate: Long = 0L,
    
    // Status
    val status: RouteStatus = RouteStatus.ACTIVE,
    val bookedByUid: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    
    // Additional info
    val notes: String = "",
    val acceptsPartialLoad: Boolean = true,
    val currentLocation: GeoPoint? = null
) {
    @get:PropertyName("isAvailable")
    val isAvailable: Boolean
        get() = status == RouteStatus.ACTIVE && availableCapacityTons > 0
}

// ═══════════════════════════════════════════════════════════════════════════
// BUSINESS OWNER - Posts Cargo Requests
// ═══════════════════════════════════════════════════════════════════════════

enum class GoodsType(val displayNameEn: String) {
    AGRICULTURAL("Agricultural Products"),
    MACHINERY("Machinery & Equipment"),
    CONSTRUCTION("Construction Materials"),
    FOOD_BEVERAGES("Food & Beverages"),
    CHEMICALS("Chemicals"),
    TEXTILES("Textiles & Garments"),
    ELECTRONICS("Electronics"),
    FURNITURE("Furniture"),
    MEDICAL("Medical Supplies"),
    GENERAL("General Goods"),
    REFRIGERATED("Refrigerated / Cold Chain"),
    HAZARDOUS("Hazardous Materials"),
    INDUSTRIAL("Industrial Equipment"),
    AUTOMOBILE("Automobile Parts")
}

enum class CargoStatus {
    OPEN,        // Looking for fleet owner
    PENDING,     // Has interested fleet owners
    BOOKED,      // Assigned to a truck
    IN_TRANSIT,  // Being transported
    DELIVERED,   // Completed
    CANCELLED
}

/**
 * Posted by BUSINESS_OWNER: "I need to transport X tons from A to B"
 */
data class CargoRequest(
    val cargoId: String = "",
    val businessOwnerUid: String = "",
    val businessOwnerName: String = "",
    val businessOwnerPhone: String = "",
    val businessOwnerCompany: String = "",
    
    // Cargo details
    val goodsType: GoodsType = GoodsType.GENERAL,
    val goodsDescription: String = "",
    val weightTons: Float = 0f,
    val specialRequirements: String = "",
    
    // Route
    val pickupLocation: GeoPoint? = null,
    val pickupAddress: String = "",
    val pickupCity: String = "",
    val deliveryLocation: GeoPoint? = null,
    val deliveryAddress: String = "",
    val deliveryCity: String = "",
    val estimatedDistanceKm: Float = 0f,
    
    // Requirements
    val requiredVehicleType: VehicleType = VehicleType.TRUCK,
    val pickupDate: Long = 0L,
    val budgetAmount: Double = 0.0,
    val currency: String = "INR",
    
    // Status
    val status: CargoStatus = CargoStatus.OPEN,
    val assignedRouteId: String = "",
    val assignedFleetOwnerUid: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    
    val imageUrls: List<String> = emptyList()
)

// ═══════════════════════════════════════════════════════════════════════════
// BOOKING / INTEREST
// ═══════════════════════════════════════════════════════════════════════════

enum class InterestStatus {
    PENDING,    // Waiting for response
    ACCEPTED,   // Both parties agreed
    REJECTED,   // Declined
    WITHDRAWN   // Interest cancelled
}

/**
 * When business owner shows interest in a truck route
 * OR when fleet owner shows interest in a cargo request
 */
data class BookingInterest(
    val interestId: String = "",
    val initiatorUid: String = "",
    val initiatorName: String = "",
    val initiatorPhone: String = "",
    val initiatorRole: UserRole = UserRole.BUSINESS_OWNER,
    
    val targetUid: String = "",
    val targetName: String = "",
    val targetRole: UserRole = UserRole.FLEET_OWNER,
    
    // Either routeId or cargoId will be filled
    val routeId: String = "",
    val cargoId: String = "",
    
    val message: String = "",
    val offeredPrice: Double = 0.0,
    val status: InterestStatus = InterestStatus.PENDING,
    val rejectionReason: String = "",  // Task 10: Reason for rejection
    val createdAt: Long = System.currentTimeMillis()
)

// ═══════════════════════════════════════════════════════════════════════════
// FLEET OWNER PROFILE (for ratings/reviews)
// ═══════════════════════════════════════════════════════════════════════════

data class FleetOwnerProfile(
    val uid: String = "",
    val name: String = "",
    val phone: String = "",
    val companyName: String = "",
    val profileImageUrl: String = "",
    val isVerified: Boolean = false,
    
    // Fleet info
    val totalVehicles: Int = 0,
    val vehicleTypes: List<VehicleType> = emptyList(),
    
    // Stats
    val totalTrips: Int = 0,
    val completedTrips: Int = 0,
    val cancelledTrips: Int = 0,
    val onTimeDeliveries: Int = 0,
    val ratingsSum: Float = 0f,
    val ratingsCount: Int = 0,
    val yearsInBusiness: Int = 0
) {
    val averageRating: Float
        get() = if (ratingsCount > 0) ratingsSum / ratingsCount else 0f
    
    val completionRate: Float
        get() = if (totalTrips > 0) (completedTrips.toFloat() / totalTrips) * 100f else 0f
    
    val onTimeRate: Float
        get() = if (completedTrips > 0) (onTimeDeliveries.toFloat() / completedTrips) * 100f else 0f
    
    val reliabilityScore: Float
        get() {
            if (totalTrips == 0) return 0f
            val ratingScore = (averageRating / 5f) * 30f
            val onTimeScore = (onTimeRate / 100f) * 25f
            val completionScore = (completionRate / 100f) * 25f
            val cancelPenalty = ((cancelledTrips.toFloat() / totalTrips) * 100f) * 0.15f
            val verifiedBonus = if (isVerified) 10f else 0f
            return (ratingScore + onTimeScore + completionScore - cancelPenalty + verifiedBonus)
                .coerceIn(0f, 100f)
        }
}

// ═══════════════════════════════════════════════════════════════════════════
// NOTIFICATIONS
// ═══════════════════════════════════════════════════════════════════════════

enum class NotificationType {
    INTEREST_RECEIVED,
    INTEREST_ACCEPTED,
    INTEREST_REJECTED,
    ROUTE_BOOKED,
    CARGO_BOOKED,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED
}

data class notifications(
    val id: String = "",
    val recipientUid: String = "",
    val type: NotificationType = NotificationType.INTEREST_RECEIVED,
    val title: String = "",
    val body: String = "",
    val relatedRouteId: String = "",
    val relatedCargoId: String = "",
    val relatedInterestId: String = "",
    val isRead: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

// ═══════════════════════════════════════════════════════════════════════════
// LANGUAGE SUPPORT
// ═══════════════════════════════════════════════════════════════════════════

enum class AppLanguage(val code: String, val displayName: String) {
    ENGLISH("en", "English"),
    HINDI("hi", "हिंदी"),
    MARATHI("mr", "मराठी"),
    TELUGU("te", "తెలుగు")
}

