# FreightConnect - Android App (Kotlin + XML)

## 🚚 Overview
A **dual-sided marketplace** connecting **Business Owners** (who need goods transported) with **Fleet Owners** (who have trucks/vehicles available for transport).

---

## ✨ Key Features

### For Business Owners:
- ✅ Post cargo transport requests (goods type, weight, pickup/delivery location, budget)
- ✅ Browse available truck routes posted by fleet owners
- ✅ Send interest/booking requests to fleet owners
- ✅ Direct call feature after acceptance
- ✅ Track cargo status (Open → Booked → In Transit → Delivered)

### For Fleet Owners:
- ✅ Post available truck routes (from city → to city, vehicle type, capacity, price per ton)
- ✅ Browse cargo requests from business owners
- ✅ Send interest to cargo requests
- ✅ Accept/reject booking requests
- ✅ Track route bookings and earnings

### Core Features:
- ✅ **Multi-language support** (English, Hindi, Marathi, Telugu)
- ✅ **Firebase Authentication** (Email/Password)
- ✅ **Firestore Database** for real-time data
- ✅ **Push Notifications** (FCM) for interests and bookings
- ✅ **Direct calling** feature (Intent to dial)
- ✅ **Role-based navigation** (different UI for each role)
- ✅ **Interest management system** (send, accept, reject)

---

## 📁 Complete Project Structure

```
FreightConnect/
├── build.gradle                    ← Root Gradle config
├── settings.gradle
├── gradle.properties
├── gradle/wrapper/
│   └── gradle-wrapper.properties
│
└── app/
    ├── build.gradle                ← App dependencies
    ├── proguard-rules.pro
    │
    └── src/main/
        ├── AndroidManifest.xml
        │
        ├── java/com/freightconnect/
        │   │
        │   ├── FreightApp.kt       ← Application class
        │   │
        │   ├── model/
        │   │   └── Models.kt       ← All data models
        │   │       ├── User, UserRole (BUSINESS_OWNER, FLEET_OWNER)
        │   │       ├── TruckRoute (posted by fleet owners)
        │   │       ├── CargoRequest (posted by business owners)
        │   │       ├── BookingInterest (interest system)
        │   │       ├── FleetOwnerProfile (ratings, stats)
        │   │       ├── VehicleType, GoodsType enums
        │   │       └── AppNotification
        │   │
        │   ├── repository/
        │   │   └── FreightRepository.kt  ← All Firestore operations
        │   │
        │   ├── viewmodel/
        │   │   ├── AuthViewModel.kt
        │   │   ├── ViewModels.kt    ← All other ViewModels
        │   │   │   ├── MainViewModel
        │   │   │   ├── FleetHomeViewModel
        │   │   │   ├── BusinessHomeViewModel
        │   │   │   ├── SearchRoutesViewModel
        │   │   │   ├── SearchCargosViewModel
        │   │   │   ├── PostRouteViewModel
        │   │   │   └── PostCargoViewModel
        │   │
        │   ├── ui/
        │   │   ├── activities/
        │   │   │   ├── SplashActivity.kt
        │   │   │   ├── AuthActivity.kt
        │   │   │   └── MainActivity.kt
        │   │   │
        │   │   ├── fragments/
        │   │   │   ├── FleetHomeFragment.kt
        │   │   │   ├── BusinessHomeFragment.kt
        │   │   │   ├── SearchRoutesFragment.kt
        │   │   │   ├── SearchCargosFragment.kt
        │   │   │   ├── PostRouteFragment.kt
        │   │   │   ├── PostCargoFragment.kt
        │   │   │   ├── RouteDetailFragment.kt
        │   │   │   ├── CargoDetailFragment.kt
        │   │   │   ├── InterestsFragment.kt
        │   │   │   ├── NotificationsFragment.kt
        │   │   │   └── ProfileFragment.kt
        │   │   │
        │   │   └── adapters/
        │   │       ├── RouteAdapter.kt
        │   │       ├── CargoAdapter.kt
        │   │       └── InterestAdapter.kt
        │   │
        │   ├── utils/
        │   │   └── LanguageHelper.kt  ← Multi-language support
        │   │
        │   └── network/
        │       └── FCMService.kt      ← Push notifications
        │
        └── res/
            ├── layout/
            │   ├── activity_splash.xml
            │   ├── activity_auth.xml
            │   ├── activity_main.xml
            │   ├── fragment_fleet_home.xml
            │   ├── fragment_business_home.xml
            │   ├── fragment_search_routes.xml
            │   ├── fragment_search_cargos.xml
            │   ├── fragment_post_route.xml
            │   ├── fragment_post_cargo.xml
            │   ├── fragment_route_detail.xml
            │   ├── fragment_cargo_detail.xml
            │   ├── fragment_interests.xml
            │   ├── fragment_notifications.xml
            │   ├── fragment_profile.xml
            │   ├── item_route_card.xml
            │   ├── item_cargo_card.xml
            │   └── item_interest_card.xml
            │
            ├── navigation/
            │   ├── nav_graph_business.xml
            │   └── nav_graph_fleet.xml
            │
            ├── menu/
            │   ├── menu_bottom_business.xml
            │   └── menu_bottom_fleet.xml
            │
            ├── values/
            │   ├── colors.xml
            │   ├── strings.xml (English)
            │   └── themes.xml
            │
            ├── values-hi/       ← Hindi strings
            │   └── strings.xml
            │
            ├── values-mr/       ← Marathi strings
            │   └── strings.xml
            │
            ├── values-te/       ← Telugu strings
            │   └── strings.xml
            │
            ├── drawable/
            │   └── [icons and graphics]
            │
            └── xml/
                ├── backup_rules.xml
                └── data_extraction_rules.xml
```

---

## 🏗️ Architecture

**Pattern:** MVVM (Model-View-ViewModel)

```
UI (Activity/Fragment) → ViewModel → Repository → Firebase
                            ↑
                       LiveData/Flow
```

- **3 Activities:** Splash, Auth, Main
- **11+ Fragments** via Navigation Component
- **Role-based navigation:** Different graphs for Business vs Fleet owners
- **Firebase Firestore** as backend database
- **Firebase Auth** for user authentication
- **FCM** for push notifications

---

## 🔥 Firestore Collections

```
/users/{uid}
    - name, email, phone, role (BUSINESS_OWNER | FLEET_OWNER)
    - companyName, preferredLanguage, fcmToken

/truckRoutes/{routeId}
    - fleetOwnerUid, fromCity, toCity
    - vehicleType, availableCapacityTons
    - pricePerTon, departureDate
    - status (ACTIVE | BOOKED | IN_TRANSIT | COMPLETED)

/cargoRequests/{cargoId}
    - businessOwnerUid, pickupCity, deliveryCity
    - goodsType, weightTons
    - budgetAmount, pickupDate
    - status (OPEN | PENDING | BOOKED | IN_TRANSIT | DELIVERED)

/bookingInterests/{interestId}
    - initiatorUid, targetUid
    - routeId OR cargoId
    - message, offeredPrice
    - status (PENDING | ACCEPTED | REJECTED)

/fleetProfiles/{uid}
    - totalTrips, completedTrips, onTimeDeliveries
    - ratingsSum, ratingsCount
    - reliabilityScore (computed)

/notifications/{notificationId}
    - recipientUid, type, title, body
    - isRead, createdAt
```

---

## 🌍 Multi-Language Support

**Supported Languages:**
- 🇬🇧 English (default)
- 🇮🇳 Hindi (हिंदी)
- 🇮🇳 Marathi (मराठी)
- 🇮🇳 Telugu (తెలుగు)

**Implementation:**
- Language selector in AuthActivity
- Stored in SharedPreferences
- Applied via `LanguageHelper.setLocale()`
- Separate `values-{lang}/strings.xml` files

---

## 📱 Screen Flow

### Business Owner Flow:
```
Auth → BusinessHomeFragment (My Cargo Requests)
  ├── Post Cargo → PostCargoFragment
  ├── Search Routes → SearchRoutesFragment
  │     └── Route Detail → Send Interest → Fleet Owner gets notified
  └── View Interests → Accept/Reject from Fleet Owners
```

### Fleet Owner Flow:
```
Auth → FleetHomeFragment (My Routes)
  ├── Post Route → PostRouteFragment
  ├── Search Cargo → SearchCargosFragment
  │     └── Cargo Detail → Send Interest → Business Owner gets notified
  └── View Interests → Accept/Reject from Business Owners
```

### Interest & Booking Flow:
```
1. Fleet Owner posts route: "Pune → Mumbai, 10 tons, ₹500/ton"
2. Business Owner finds it and sends interest
3. Fleet Owner receives notification
4. Fleet Owner accepts → Contact details shared
5. Both parties can now call each other directly
6. Status updated: BOOKED → IN_TRANSIT → COMPLETED
```

---

## 🚀 Setup Instructions

### 1. Firebase Setup
1. Create project at [console.firebase.google.com](https://console.firebase.google.com)
2. Add Android app with package: `com.freightconnect`
3. Download `google-services.json` → place in `app/`
4. Enable **Authentication** → Email/Password
5. Create **Firestore Database** (start in test mode)
6. Enable **Cloud Messaging** (FCM)

### 2. Firestore Security Rules (Production)
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read: if request.auth != null;
      allow write: if request.auth.uid == userId;
    }
    
    match /truckRoutes/{routeId} {
      allow read: if request.auth != null;
      allow create: if request.auth.uid == request.resource.data.fleetOwnerUid;
      allow update, delete: if request.auth.uid == resource.data.fleetOwnerUid;
    }
    
    match /cargoRequests/{cargoId} {
      allow read: if request.auth != null;
      allow create: if request.auth.uid == request.resource.data.businessOwnerUid;
      allow update, delete: if request.auth.uid == resource.data.businessOwnerUid;
    }
    
    match /bookingInterests/{interestId} {
      allow read: if request.auth != null;
      allow create: if request.auth != null;
      allow update: if request.auth.uid == resource.data.initiatorUid 
                    || request.auth.uid == resource.data.targetUid;
    }
  }
}
```

### 3. Build & Run
```bash
# Open in Android Studio
# Sync Gradle
# Run on emulator or device (API 24+)
```

---

## 📦 Dependencies (Key Libraries)

```gradle
// Firebase
firebase-auth, firebase-firestore, firebase-messaging, firebase-storage

// AndroidX
navigation-fragment-ktx, lifecycle-viewmodel-ktx, coroutines

// UI
material:1.11.0, glide:4.16.0, circleimageview, lottie

// Utils
libphonenumber (for phone formatting)
```

---

## 🔔 Push Notifications

**FCMService** handles:
- New interest received
- Interest accepted/rejected
- Route/cargo status updates

**Notification types:**
- `INTEREST_RECEIVED` - "John sent interest in your route"
- `INTEREST_ACCEPTED` - "Your interest was accepted! Call now"
- `ROUTE_BOOKED` - "Your route is booked by XYZ Ltd"
- `IN_TRANSIT`, `DELIVERED`, `CANCELLED`

---

## 📞 Direct Calling Feature

After interest acceptance:
```kotlin
// Both parties get contact details
val intent = Intent(Intent.ACTION_DIAL).apply {
    data = Uri.parse("tel:${phoneNumber}")
}
startActivity(intent)
```

---

## 🎨 UI/UX Highlights

- **Material Design 3** components
- **Role-specific navigation** (different bottom nav for each role)
- **Status color coding** (Active=Blue, Booked=Green, InTransit=Purple, etc.)
- **Shimmer loading** placeholders
- **Swipe-to-refresh** on list screens
- **Multi-language** UI adaptation

---

## 🔐 Security Notes

- All Firestore operations require authentication
- Phone numbers stored with country code
- FCM tokens updated on login
- Password reset via Firebase Auth email
- Production: Add proper security rules (see above)

---

## 📊 Future Enhancements

- [ ] Google Maps integration for route visualization
- [ ] Real-time location tracking during transit
- [ ] Payment gateway integration
- [ ] Driver verification (KYC documents)
- [ ] In-app chat between parties
- [ ] Load/unload photo evidence
- [ ] E-way bill integration
- [ ] Analytics dashboard

---

## 📄 License

Proprietary - All rights reserved

---

## 👨‍💻 Developer Notes

- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.9.22
- Gradle: 8.2.2
- JDK: 17

---

## 🆘 Troubleshooting

**Issue:** App crashes on startup
**Fix:** Ensure `google-services.json` is in `app/` folder

**Issue:** Language not changing
**Fix:** Check `LanguageHelper.setLocale()` is called before `setContentView()`

**Issue:** Notifications not working
**Fix:** Verify FCM setup and token saving in Firestore

---

## 📞 Support

For issues or questions, check Firebase console logs and Logcat in Android Studio.
