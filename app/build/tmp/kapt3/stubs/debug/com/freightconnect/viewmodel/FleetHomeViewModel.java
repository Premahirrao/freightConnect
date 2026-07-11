package com.freightconnect.viewmodel;

/**
 * FleetHomeViewModel - Fleet Owner's dashboard and route management
 *
 * Features:
 * - Real-time vehicle tracking
 * - Route management (my posted routes)
 * - Interest management (incoming booking requests)
 * - Dashboard metrics (total vehicles, completed trips, routes, pending interests)
 *
 * Real-time Listeners:
 * - vehiclesListener: Tracks fleet vehicles
 * - routesListener: Tracks posted routes
 * - interestsListener: Tracks incoming booking interests
 *
 * Used by: FleetHomeFragment
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\"H\u0014J\u0006\u0010$\u001a\u00020\"J\u0016\u0010%\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\b0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012\u00a8\u0006\'"}, d2 = {"Lcom/freightconnect/viewmodel/FleetHomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_completedTrips", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_isLoading", "", "_myRoutes", "", "Lcom/freightconnect/model/TruckRoute;", "_receivedInterests", "Lcom/freightconnect/model/BookingInterest;", "_totalPostedRoutes", "completedTrips", "Landroidx/lifecycle/LiveData;", "getCompletedTrips", "()Landroidx/lifecycle/LiveData;", "currentUid", "", "interestsListener", "Lcom/google/firebase/firestore/ListenerRegistration;", "isLoading", "myRoutes", "getMyRoutes", "receivedInterests", "getReceivedInterests", "repo", "Lcom/freightconnect/repository/FreightRepository;", "routesListener", "totalPostedRoutes", "getTotalPostedRoutes", "loadDashboardData", "", "onCleared", "refreshData", "updateRouteMetrics", "routes", "app_debug"})
public final class FleetHomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentUid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.freightconnect.model.TruckRoute>> _myRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.TruckRoute>> myRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.freightconnect.model.BookingInterest>> _receivedInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.BookingInterest>> receivedInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _completedTrips = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> completedTrips = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _totalPostedRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalPostedRoutes = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration routesListener;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration interestsListener;
    
    public FleetHomeViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.TruckRoute>> getMyRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.BookingInterest>> getReceivedInterests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCompletedTrips() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalPostedRoutes() {
        return null;
    }
    
    public final void loadDashboardData() {
    }
    
    public final void refreshData() {
    }
    
    private final void updateRouteMetrics(java.util.List<com.freightconnect.model.TruckRoute> routes) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}