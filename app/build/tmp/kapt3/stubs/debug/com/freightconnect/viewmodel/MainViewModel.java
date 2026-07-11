package com.freightconnect.viewmodel;

/**
 * MainViewModel - Manages overall app state
 *
 * Responsibilities:
 * - Load currently logged-in user
 * - Maintain user session state
 * - Handle logout
 *
 * Used by: MainActivity
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/freightconnect/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_currentUser", "Landroidx/lifecycle/MutableLiveData;", "Lcom/freightconnect/model/User;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "currentUser", "Landroidx/lifecycle/LiveData;", "getCurrentUser", "()Landroidx/lifecycle/LiveData;", "repo", "Lcom/freightconnect/repository/FreightRepository;", "loadCurrentUser", "", "logout", "updateUserLanguage", "languageCode", "", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.freightconnect.model.User> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.freightconnect.model.User> currentUser = null;
    
    public MainViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.freightconnect.model.User> getCurrentUser() {
        return null;
    }
    
    private final void loadCurrentUser() {
    }
    
    public final void logout() {
    }
    
    public final void updateUserLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
    }
}