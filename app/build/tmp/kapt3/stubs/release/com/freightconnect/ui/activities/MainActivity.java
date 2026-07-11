package com.freightconnect.ui.activities;

/**
 * MainActivity - Central hub for authenticated users
 *
 * FLOW: SplashActivity → AuthActivity → MainActivity
 *
 * Responsibilities:
 * - Manage role-based navigation graphs (Business vs Fleet owner)
 * - Handle session management and user state
 * - Control bottom navigation visibility based on screen
 *
 * Navigation Architecture:
 * - nav_graph_business.xml: For Business Owners (post cargo, search routes)
 * - nav_graph_fleet.xml: For Fleet Owners (post routes, search cargos)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lcom/freightconnect/ui/activities/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/freightconnect/databinding/ActivityMainBinding;", "navController", "Landroidx/navigation/NavController;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "viewModel", "Lcom/freightconnect/viewmodel/MainViewModel;", "getViewModel", "()Lcom/freightconnect/viewmodel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "askNotificationPermission", "", "navigateToAuth", "observeUserAndInitializeGraph", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupNavigation", "showNotificationPermissionRationale", "app_release"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.freightconnect.databinding.ActivityMainBinding binding;
    private androidx.navigation.NavController navController;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestPermissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.freightconnect.viewmodel.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Request notification permission for Android 13+ (API 33+)
     * In Android 13+, POST_NOTIFICATIONS is a runtime permission
     */
    private final void askNotificationPermission() {
    }
    
    /**
     * Show educational dialog explaining the importance of notifications
     * before requesting the permission
     */
    private final void showNotificationPermissionRationale() {
    }
    
    /**
     * Setup NavController and configure navigation behavior
     */
    private final void setupNavigation() {
    }
    
    /**
     * Observe current user and set appropriate navigation graph
     * - Handles session expiration
     * - Initializes role-specific bottom navigation menu
     */
    private final void observeUserAndInitializeGraph() {
    }
    
    /**
     * Navigate to AuthActivity (session expired or logout)
     */
    private final void navigateToAuth() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}