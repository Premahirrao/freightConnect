package com.freightconnect.ui.activities;

/**
 * SplashActivity - App Entry Point
 *
 * FLOW: SplashActivity → AuthActivity or MainActivity
 *
 * Responsibilities:
 * - Display splash screen for 2 seconds
 * - Check authentication state
 * - Route to appropriate screen based on session
 *
 * Navigation Logic:
 * - If user logged in → MainActivity
 * - If user not logged in → AuthActivity
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/freightconnect/ui/activities/SplashActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/freightconnect/databinding/ActivitySplashBinding;", "splashDurationMs", "", "navigateBasedOnAuthState", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
@android.annotation.SuppressLint(value = {"CustomSplashScreen"})
public final class SplashActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.freightconnect.databinding.ActivitySplashBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private final long splashDurationMs = 2000L;
    
    public SplashActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Navigate to the appropriate screen based on authentication state
     */
    private final void navigateBasedOnAuthState() {
    }
}