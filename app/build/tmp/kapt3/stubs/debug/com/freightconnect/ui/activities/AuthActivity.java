package com.freightconnect.ui.activities;

/**
 * AuthActivity - User Authentication & Registration
 *
 * FLOW: SplashActivity → AuthActivity → MainActivity
 *
 * Responsibilities:
 * - Handle user login and registration
 * - Manage role selection (Business Owner vs Fleet Owner)
 * - Support multi-language selection
 * - Manage password reset flow
 *
 * Features:
 * - Tab-based UI for login/register
 * - Role-based registration with role descriptions
 * - Language selection with immediate UI recreation
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\u0012\u0010!\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\b\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\b\u0010)\u001a\u00020\u001fH\u0002J\b\u0010*\u001a\u00020\u001fH\u0002J\u0010\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u0014H\u0002J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u0014H\u0002J\b\u0010/\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\u00a8\u00060"}, d2 = {"Lcom/freightconnect/ui/activities/AuthActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/freightconnect/databinding/ActivityAuthBinding;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "googleSignInLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "isLoginMode", "", "phoneCallbacks", "Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;", "phoneResendToken", "Lcom/google/firebase/auth/PhoneAuthProvider$ForceResendingToken;", "phoneVerificationId", "", "selectedLanguage", "selectedRole", "Lcom/freightconnect/model/UserRole;", "viewModel", "Lcom/freightconnect/viewmodel/AuthViewModel;", "getViewModel", "()Lcom/freightconnect/viewmodel/AuthViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "navigateToMain", "", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupAuthModeSwitcher", "setupButtons", "setupGoogleSignIn", "setupLanguageSelector", "setupRoleToggle", "showOtpDialog", "showPhoneNumberDialog", "showSnackbar", "msg", "startPhoneVerification", "phoneNumber", "updateAuthModeUi", "app_debug"})
public final class AuthActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.freightconnect.databinding.ActivityAuthBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private boolean isLoginMode = true;
    @org.jetbrains.annotations.NotNull()
    private com.freightconnect.model.UserRole selectedRole = com.freightconnect.model.UserRole.BUSINESS_OWNER;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedLanguage = "en";
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String phoneVerificationId;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken phoneResendToken;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleSignInLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneCallbacks = null;
    
    public AuthActivity() {
        super();
    }
    
    private final com.freightconnect.viewmodel.AuthViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupGoogleSignIn() {
    }
    
    /**
     * Setup language selector dropdown
     */
    private final void setupLanguageSelector() {
    }
    
    private final void setupAuthModeSwitcher() {
    }
    
    private final void updateAuthModeUi() {
    }
    
    /**
     * Setup role selection toggle (Business Owner vs Fleet Owner)
     */
    private final void setupRoleToggle() {
    }
    
    /**
     * Setup authentication buttons
     */
    private final void setupButtons() {
    }
    
    /**
     * Observe authentication state changes
     */
    private final void observeViewModel() {
    }
    
    private final void showPhoneNumberDialog() {
    }
    
    private final void showOtpDialog() {
    }
    
    private final void startPhoneVerification(java.lang.String phoneNumber) {
    }
    
    /**
     * Navigate to MainActivity with proper flags
     */
    private final void navigateToMain() {
    }
    
    /**
     * Show snackbar message
     */
    private final void showSnackbar(java.lang.String msg) {
    }
}