package com.freightconnect.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015J>\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0015J \u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010\'\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/freightconnect/viewmodel/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_authState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/freightconnect/viewmodel/AuthState;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "authState", "Landroidx/lifecycle/LiveData;", "getAuthState", "()Landroidx/lifecycle/LiveData;", "repo", "Lcom/freightconnect/repository/FreightRepository;", "ensureUserRecord", "", "firebaseUser", "Lcom/google/firebase/auth/FirebaseUser;", "defaultRole", "Lcom/freightconnect/model/UserRole;", "language", "", "(Lcom/google/firebase/auth/FirebaseUser;Lcom/freightconnect/model/UserRole;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUserLoggedIn", "", "login", "email", "password", "register", "name", "phone", "role", "company", "resetPassword", "signInWithCredential", "credential", "Lcom/google/firebase/auth/AuthCredential;", "signInWithGoogle", "idToken", "signInWithPhone", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.freightconnect.viewmodel.AuthState> _authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.freightconnect.viewmodel.AuthState> authState = null;
    
    public AuthViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.freightconnect.viewmodel.AuthState> getAuthState() {
        return null;
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole role, @org.jetbrains.annotations.NotNull()
    java.lang.String company, @org.jetbrains.annotations.NotNull()
    java.lang.String language) {
    }
    
    public final void resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole defaultRole, @org.jetbrains.annotations.NotNull()
    java.lang.String language) {
    }
    
    public final void signInWithPhone(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential credential, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole defaultRole, @org.jetbrains.annotations.NotNull()
    java.lang.String language) {
    }
    
    private final void signInWithCredential(com.google.firebase.auth.AuthCredential credential, com.freightconnect.model.UserRole defaultRole, java.lang.String language) {
    }
    
    private final java.lang.Object ensureUserRecord(com.google.firebase.auth.FirebaseUser firebaseUser, com.freightconnect.model.UserRole defaultRole, java.lang.String language, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}