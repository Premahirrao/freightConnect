package com.freightconnect.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.freightconnect.R
import com.freightconnect.databinding.ActivityMainBinding
import com.freightconnect.model.UserRole
import com.freightconnect.utils.LanguageHelper
import com.freightconnect.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    // Permission launcher for Android 13+ (TIRAMISU) notification permission
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and app) can post notifications
            // Toast or log success - user granted permission
        } else {
            // TODO: Inform user that the app will not show notifications
            // This is optional - user can still use app, just won't get notifications
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply saved language before inflating layout
        LanguageHelper.loadLocale(this)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Request notification permission for Android 13+
        askNotificationPermission()

        setupNavigation()
        observeUserAndInitializeGraph()
    }

    /**
     * Request notification permission for Android 13+ (API 33+)
     * In Android 13+, POST_NOTIFICATIONS is a runtime permission
     */
    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // FCM SDK (and app) can post notifications
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    // Display educational UI explaining why notifications are needed
                    showNotificationPermissionRationale()
                }
                else -> {
                    // Directly request the permission
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    /**
     * Show educational dialog explaining the importance of notifications
     * before requesting the permission
     */
    private fun showNotificationPermissionRationale() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Enable Notifications")
            .setMessage(
                "Notifications help you stay updated on booking interests, " +
                "route changes, and delivery status. Enable notifications?"
            )
            .setPositiveButton("Enable") { _, _ ->
                // User agreed to see the rationale - request permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            .setNegativeButton("Not Now") { dialog, _ ->
                // User declined - app will work but won't show notifications
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    /**
     * Setup NavController and configure navigation behavior
     */
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        // Dynamically hide/show bottom nav based on destination
        navController.addOnDestinationChangedListener { _, dest, _ ->
            val hideBottomNavScreens = setOf(
                R.id.routeDetailFragment,
                R.id.cargoDetailFragment,
                R.id.postRouteFragment,
                R.id.postCargoFragment,
                R.id.interestsFragment

            )
            binding.bottomNav.visibility =
                if (dest.id in hideBottomNavScreens) View.GONE else View.VISIBLE
        }
    }

    /**
     * Observe current user and set appropriate navigation graph
     * - Handles session expiration
     * - Initializes role-specific bottom navigation menu
     */
    private fun observeUserAndInitializeGraph() {
        viewModel.currentUser.observe(this) { user ->
            if (user == null) {
                // Session expired or user logged out
                navigateToAuth()
                return@observe
            }

            // Initialize navigation graph based on user role
            val navGraph = when (user.role) {
                UserRole.BUSINESS_OWNER -> R.navigation.nav_graph_business
                UserRole.FLEET_OWNER -> R.navigation.nav_graph_fleet
            }
            
            try {
                val inflater = navController.navInflater
                val graph = inflater.inflate(navGraph)
                navController.graph = graph

                // Update bottom nav menu items for the role
                binding.bottomNav.menu.clear()
                when (user.role) {
                    UserRole.BUSINESS_OWNER -> binding.bottomNav.inflateMenu(R.menu.menu_bottom_business)
                    UserRole.FLEET_OWNER -> binding.bottomNav.inflateMenu(R.menu.menu_bottom_fleet)
                }
            } catch (_: Exception) {
                // Failed to initialize graph, redirect to auth
                navigateToAuth()
            }
        }
    }

    /**
     * Navigate to AuthActivity (session expired or logout)
     */
    private fun navigateToAuth() {
        startActivity(Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
