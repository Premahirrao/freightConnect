package com.freightconnect.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.freightconnect.R
import com.freightconnect.databinding.ActivityMainBinding
import com.freightconnect.model.UserRole
import com.freightconnect.utils.LanguageHelper
import com.freightconnect.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply saved language
        LanguageHelper.loadLocale(this)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        observeUser()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        // Hide bottom nav on certain destinations
        navController.addOnDestinationChangedListener { _, dest, _ ->
            val hideNavDestinations = setOf(
                R.id.routeDetailFragment,
                R.id.cargoDetailFragment,
                R.id.postRouteFragment,
                R.id.postCargoFragment,
                R.id.interestsFragment
            )
            binding.bottomNav.visibility =
                if (dest.id in hideNavDestinations) View.GONE else View.VISIBLE
        }
    }

    private fun observeUser() {
        viewModel.currentUser.observe(this) { user ->
            if (user == null) {
                // Session expired
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
                return@observe
            }

            // Set navigation graph based on role
            val navGraph = when (user.role) {
                UserRole.BUSINESS_OWNER -> R.navigation.nav_graph_business
                UserRole.FLEET_OWNER -> R.navigation.nav_graph_fleet
            }
            val inflater = navController.navInflater
            val graph = inflater.inflate(navGraph)
            navController.graph = graph

            // Update bottom nav menu
            binding.bottomNav.menu.clear()
            when (user.role) {
                UserRole.BUSINESS_OWNER -> binding.bottomNav.inflateMenu(R.menu.menu_bottom_business)
                UserRole.FLEET_OWNER -> binding.bottomNav.inflateMenu(R.menu.menu_bottom_fleet)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
