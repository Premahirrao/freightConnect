package com.freightconnect.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010\"J\u0006\u0010#\u001a\u00020\u0011J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0086@\u00a2\u0006\u0002\u0010\'J\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020)0%2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010+\u001a\u0004\u0018\u00010)2\u0006\u0010\u0014\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010-\u001a\u0004\u0018\u00010\u001d2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020)0%H\u0086@\u00a2\u0006\u0002\u0010\'J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u0002000%2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001c\u00101\u001a\b\u0012\u0004\u0012\u0002000%2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u00102\u001a\u0004\u0018\u00010&2\u0006\u0010\u0016\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u00103\u001a\u0002042\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u00105\u001a\u0004\u0018\u00010!2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\u00190%2\u0006\u0010*\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012JB\u00107\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:JB\u0010>\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:JB\u0010?\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:JB\u0010@\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:JB\u0010A\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:JB\u0010B\u001a\u0002082\u0006\u0010*\u001a\u00020\u00112\u0018\u00109\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000%\u0012\u0004\u0012\u00020\u000f0:2\u0018\b\u0002\u0010;\u001a\u0012\u0012\b\u0012\u00060<j\u0002`=\u0012\u0004\u0012\u00020\u000f0:J\u0016\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J \u0010E\u001a\u00020\u000f2\u0006\u0010F\u001a\u0002002\b\u0010G\u001a\u0004\u0018\u00010!H\u0086@\u00a2\u0006\u0002\u0010HJ(\u0010I\u001a\u00020\u000f2\u0006\u0010F\u001a\u0002002\b\u0010G\u001a\u0004\u0018\u00010!2\u0006\u0010J\u001a\u00020\u0011H\u0082@\u00a2\u0006\u0002\u0010KJ \u0010L\u001a\u00020\u000f2\u0006\u0010F\u001a\u0002002\b\u0010M\u001a\u0004\u0018\u00010!H\u0086@\u00a2\u0006\u0002\u0010HJ \u0010N\u001a\u00020\u000f2\u0006\u0010F\u001a\u0002002\b\u0010G\u001a\u0004\u0018\u00010!H\u0086@\u00a2\u0006\u0002\u0010HJ\u0016\u0010O\u001a\u00020\u00112\u0006\u0010P\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010QJ\u0016\u0010R\u001a\u00020\u00112\u0006\u0010S\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010TJ \u0010U\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010V\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010WJ$\u0010X\u001a\b\u0012\u0004\u0012\u00020)0%2\u0006\u0010Y\u001a\u00020\u00112\u0006\u0010Z\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010WJ$\u0010[\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010Y\u001a\u00020\u00112\u0006\u0010Z\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010WJ\u0016\u0010\\\u001a\u00020\u00112\u0006\u0010F\u001a\u000200H\u0086@\u00a2\u0006\u0002\u0010]JL\u0010^\u001a\u00020\u000f2\u0006\u0010_\u001a\u00020\u00112\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u00112\u0006\u0010c\u001a\u00020\u00112\b\b\u0002\u0010d\u001a\u00020\u00112\b\b\u0002\u0010e\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010gJ\u001e\u0010h\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010i\u001a\u00020jH\u0086@\u00a2\u0006\u0002\u0010kJ\u001e\u0010l\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010m\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010WJ.\u0010n\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020p2\u0006\u0010r\u001a\u00020sH\u0086@\u00a2\u0006\u0002\u0010tJ\u001e\u0010u\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010i\u001a\u00020vH\u0086@\u00a2\u0006\u0002\u0010wJ\u001e\u0010x\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010y\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010WR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006z"}, d2 = {"Lcom/freightconnect/repository/FreightRepository;", "", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "cargoRequestsRef", "Lcom/google/firebase/firestore/CollectionReference;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "fleetProfilesRef", "interestsRef", "notificationsRef", "truckRoutesRef", "usersRef", "acceptInterest", "", "interestId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelCargo", "cargoId", "cancelRoute", "routeId", "createNotification", "notification", "Lcom/freightconnect/model/notifications;", "(Lcom/freightconnect/model/notifications;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createOrUpdateFleetProfile", "profile", "Lcom/freightconnect/model/FleetOwnerProfile;", "(Lcom/freightconnect/model/FleetOwnerProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createUser", "user", "Lcom/freightconnect/model/User;", "(Lcom/freightconnect/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentUid", "getActiveRoutes", "", "Lcom/freightconnect/model/TruckRoute;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBusinessOwnerCargos", "Lcom/freightconnect/model/CargoRequest;", "uid", "getCargoRequest", "getFleetOwnerRoutes", "getFleetProfile", "getOpenCargos", "getReceivedInterests", "Lcom/freightconnect/model/BookingInterest;", "getSentInterests", "getTruckRoute", "getUnreadInterestCount", "", "getUser", "getUserNotifications", "listenToBusinessCargos", "Lcom/google/firebase/firestore/ListenerRegistration;", "onUpdate", "Lkotlin/Function1;", "onError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "listenToBusinessInterests", "listenToFleetInterests", "listenToFleetRoutes", "listenToReceivedInterests", "listenToSentInterests", "markNotificationAsRead", "notificationId", "notifyInterestAccepted", "interest", "initiatorUser", "(Lcom/freightconnect/model/BookingInterest;Lcom/freightconnect/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyInterestCancelled", "itemType", "(Lcom/freightconnect/model/BookingInterest;Lcom/freightconnect/model/User;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyInterestReceived", "recipientUser", "notifyInterestRejected", "postCargoRequest", "cargo", "(Lcom/freightconnect/model/CargoRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postTruckRoute", "route", "(Lcom/freightconnect/model/TruckRoute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rejectInterest", "reason", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCargosByCity", "fromCity", "toCity", "searchRoutesByCity", "sendInterest", "(Lcom/freightconnect/model/BookingInterest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendNotification", "recipientUid", "type", "Lcom/freightconnect/model/NotificationType;", "title", "body", "relatedRouteId", "relatedCargoId", "relatedInterestId", "(Ljava/lang/String;Lcom/freightconnect/model/NotificationType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCargoStatus", "status", "Lcom/freightconnect/model/CargoStatus;", "(Ljava/lang/String;Lcom/freightconnect/model/CargoStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFCMToken", "token", "updateFleetStats", "completedTrip", "", "onTime", "rating", "", "(Ljava/lang/String;ZZFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRouteStatus", "Lcom/freightconnect/model/RouteStatus;", "(Ljava/lang/String;Lcom/freightconnect/model/RouteStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserLanguage", "languageCode", "app_release"})
public final class FreightRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference usersRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference truckRoutesRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference cargoRequestsRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference interestsRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference fleetProfilesRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference notificationsRef = null;
    
    public FreightRepository() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createUser(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.freightconnect.model.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUserLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String languageCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFCMToken(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentUid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object postTruckRoute(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.TruckRoute route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFleetOwnerRoutes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.TruckRoute>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getActiveRoutes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.TruckRoute>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchRoutesByCity(@org.jetbrains.annotations.NotNull()
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull()
    java.lang.String toCity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.TruckRoute>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTruckRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.freightconnect.model.TruckRoute> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRouteStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.RouteStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cancelRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object postCargoRequest(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.CargoRequest cargo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBusinessOwnerCargos(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.CargoRequest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOpenCargos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.CargoRequest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchCargosByCity(@org.jetbrains.annotations.NotNull()
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull()
    java.lang.String toCity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.CargoRequest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCargoRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.freightconnect.model.CargoRequest> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCargoStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.CargoStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cancelCargo(@org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendInterest(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.BookingInterest interest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getReceivedInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.BookingInterest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSentInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.BookingInterest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object acceptInterest(@org.jetbrains.annotations.NotNull()
    java.lang.String interestId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object rejectInterest(@org.jetbrains.annotations.NotNull()
    java.lang.String interestId, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToFleetRoutes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.TruckRoute>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToFleetInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.BookingInterest>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToBusinessCargos(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.CargoRequest>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToBusinessInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.BookingInterest>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFleetProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.freightconnect.model.FleetOwnerProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createOrUpdateFleetProfile(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.FleetOwnerProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFleetStats(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, boolean completedTrip, boolean onTime, float rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Task 11: Listen to received interests in real-time (replaces polling)
     * Emits updates whenever received interests change
     */
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToReceivedInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.BookingInterest>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    /**
     * Task 11: Listen to sent interests in real-time (replaces polling)
     * Emits updates whenever sent interests change
     */
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.ListenerRegistration listenToSentInterests(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.freightconnect.model.BookingInterest>, kotlin.Unit> onUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onError) {
        return null;
    }
    
    /**
     * Task 11: Get unread interest count (pending interests received)
     * Used for badge counter
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUnreadInterestCount(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Task 12: Create and send a notification to user
     * Called when interest is received, accepted, rejected, etc
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String recipientUid, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.NotificationType type, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body, @org.jetbrains.annotations.NotNull()
    java.lang.String relatedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String relatedCargoId, @org.jetbrains.annotations.NotNull()
    java.lang.String relatedInterestId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Task 12: Notify recipient when interest is received
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object notifyInterestReceived(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.BookingInterest interest, @org.jetbrains.annotations.Nullable()
    com.freightconnect.model.User recipientUser, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Task 12: Notify initiator when interest is accepted
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object notifyInterestAccepted(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.BookingInterest interest, @org.jetbrains.annotations.Nullable()
    com.freightconnect.model.User initiatorUser, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Task 12: Notify initiator when interest is rejected
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object notifyInterestRejected(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.BookingInterest interest, @org.jetbrains.annotations.Nullable()
    com.freightconnect.model.User initiatorUser, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object notifyInterestCancelled(com.freightconnect.model.BookingInterest interest, com.freightconnect.model.User initiatorUser, java.lang.String itemType, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createNotification(@org.jetbrains.annotations.NotNull()
    com.freightconnect.model.notifications notification, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserNotifications(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.freightconnect.model.notifications>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markNotificationAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String notificationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}