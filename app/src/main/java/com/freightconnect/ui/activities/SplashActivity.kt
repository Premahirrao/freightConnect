package com.freightconnect.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.freightconnect.databinding.ActivitySplashBinding
import com.freightconnect.utils.LanguageHelper
import com.google.firebase.auth.FirebaseAuth

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
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val auth = FirebaseAuth.getInstance()
    private val splashDurationMs = 2000L  // 2 second splash screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply saved language before inflating layout
        LanguageHelper.loadLocale(this)
        
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Schedule navigation after splash duration
        Handler(Looper.getMainLooper()).postDelayed({
            navigateBasedOnAuthState()
        }, splashDurationMs)
    }

    /**
     * Navigate to the appropriate screen based on authentication state
     */
    private fun navigateBasedOnAuthState() {
        val intent = if (auth.currentUser != null) {
            // User is logged in - proceed to main app
            Intent(this, MainActivity::class.java)
        } else {
            // User is not logged in - go to authentication
            Intent(this, AuthActivity::class.java)
        }

        // Use flags to prevent back navigation to splash
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
