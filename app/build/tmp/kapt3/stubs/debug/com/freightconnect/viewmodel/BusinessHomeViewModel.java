package com.freightconnect.viewmodel;

/**
 * BusinessHomeViewModel - Business Owner's cargo management and dashboard
 *
 * Features:
 * - Cargo request management (my posted cargos)
 * - Interest management (incoming booking requests from fleet owners)
 * - Dashboard metrics (total cargo, open, pending, booked)
 * - Real-time updates via Firestore listeners
 *
 * Real-time Listeners:
 * - cargosListener: Tracks posted cargo requests
 * - interestsListener: Tracks incoming booking interests
 *
 * Used by: BusinessHomeFragment
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\'\u001a\u00020(J\b\u0010)\u001a\u00020(H\u0014J\u0006\u0010*\u001a\u00020(J\u0016\u0010+\u001a\u00020(2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\b0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0014\u00a8\u0006-"}, d2 = {"Lcom/freightconnect/viewmodel/BusinessHomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_bookedCargos", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_isLoading", "", "_myCargos", "", "Lcom/freightconnect/model/CargoRequest;", "_openCargos", "_pendingCargos", "_receivedInterests", "Lcom/freightconnect/model/BookingInterest;", "_totalCargos", "bookedCargos", "Landroidx/lifecycle/LiveData;", "getBookedCargos", "()Landroidx/lifecycle/LiveData;", "cargosListener", "Lcom/google/firebase/firestore/ListenerRegistration;", "currentUid", "", "interestsListener", "isLoading", "myCargos", "getMyCargos", "openCargos", "getOpenCargos", "pendingCargos", "getPendingCargos", "receivedInterests", "getReceivedInterests", "repo", "Lcom/freightconnect/repository/FreightRepository;", "totalCargos", "getTotalCargos", "loadDashboardData", "", "onCleared", "refreshData", "updateCargoMetrics", "cargos", "app_debug"})
public final class BusinessHomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentUid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.freightconnect.model.CargoRequest>> _myCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.CargoRequest>> myCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.freightconnect.model.BookingInterest>> _receivedInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.BookingInterest>> receivedInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _totalCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _openCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> openCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _pendingCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> pendingCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _bookedCargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> bookedCargos = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration cargosListener;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration interestsListener;
    
    public BusinessHomeViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.CargoRequest>> getMyCargos() {
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
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalCargos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getOpenCargos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getPendingCargos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getBookedCargos() {
        return null;
    }
    
    public final void loadDashboardData() {
    }
    
    public final void refreshData() {
    }
    
    private final void updateCargoMetrics(java.util.List<com.freightconnect.model.CargoRequest> cargos) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}