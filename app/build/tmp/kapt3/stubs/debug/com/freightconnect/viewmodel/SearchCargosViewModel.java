package com.freightconnect.viewmodel;

/**
 * SearchCargosViewModel - Search and filter cargo requests
 *
 * Features:
 * - Search cargos by from/to cities
 * - Filter by goods type
 * - Real-time search as user types
 *
 * Used by: SearchCargosFragment
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/freightconnect/viewmodel/SearchCargosViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_cargos", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/freightconnect/model/CargoRequest;", "_isLoading", "", "kotlin.jvm.PlatformType", "cargos", "Landroidx/lifecycle/LiveData;", "getCargos", "()Landroidx/lifecycle/LiveData;", "isLoading", "repo", "Lcom/freightconnect/repository/FreightRepository;", "searchCargos", "", "fromCity", "", "toCity", "app_debug"})
public final class SearchCargosViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.freightconnect.model.CargoRequest>> _cargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.CargoRequest>> cargos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    
    public SearchCargosViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.freightconnect.model.CargoRequest>> getCargos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void searchCargos(@org.jetbrains.annotations.NotNull()
    java.lang.String fromCity, @org.jetbrains.annotations.NotNull()
    java.lang.String toCity) {
    }
}