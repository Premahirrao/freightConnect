package com.freightconnect.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.freightconnect.R
import com.freightconnect.databinding.ActivityAuthBinding
import com.freightconnect.model.AppLanguage
import com.freightconnect.model.UserRole
import com.freightconnect.utils.LanguageHelper
import com.freightconnect.viewmodel.AuthState
import com.freightconnect.viewmodel.AuthViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

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
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    private var isLoginMode = true
    private var selectedRole = UserRole.BUSINESS_OWNER
    private var selectedLanguage = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply saved language before inflating layout
        LanguageHelper.loadLocale(this)
        
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If already logged in, navigate directly to main
        if (viewModel.isUserLoggedIn()) {
            navigateToMain()
            return
        }

        setupLanguageSelector()
        setupTabs()
        setupRoleToggle()
        setupButtons()
        observeViewModel()
    }

    /**
     * Setup language selector dropdown
     */
    private fun setupLanguageSelector() {
        val languages = AppLanguage.entries.map { it.displayName }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, languages)
        binding.actvLanguage.setAdapter(adapter)
        binding.actvLanguage.setText(AppLanguage.ENGLISH.displayName, false)
        
        binding.actvLanguage.setOnItemClickListener { _, _, position, _ ->
            selectedLanguage = AppLanguage.entries[position].code
            LanguageHelper.setLocale(this, selectedLanguage)
            recreate() // Recreate to apply language change
        }
    }

    /**
     * Setup login/register tabs
     */
    private fun setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                isLoginMode = tab.position == 0
                
                // Toggle visibility of registration-only fields
                binding.layoutName.visibility = if (isLoginMode) View.GONE else View.VISIBLE
                binding.layoutPhone.visibility = if (isLoginMode) View.GONE else View.VISIBLE
                binding.layoutCompany.visibility = if (isLoginMode) View.GONE else View.VISIBLE
                binding.layoutRoleSelector.visibility = if (isLoginMode) View.GONE else View.VISIBLE
                
                // Update button text
                binding.btnAuth.text = if (isLoginMode) 
                    getString(R.string.login) else getString(R.string.register)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    /**
     * Setup role selection toggle (Business Owner vs Fleet Owner)
     */
    private fun setupRoleToggle() {
        binding.toggleRole.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                selectedRole = when (checkedId) {
                    R.id.btnRoleFleet -> UserRole.FLEET_OWNER
                    else -> UserRole.BUSINESS_OWNER
                }
                binding.tvRoleDesc.text = when (selectedRole) {
                    UserRole.FLEET_OWNER -> getString(R.string.role_fleet_desc)
                    UserRole.BUSINESS_OWNER -> getString(R.string.role_business_desc)
                }
            }
        }
        binding.toggleRole.check(R.id.btnRoleBusiness)
    }

    /**
     * Setup authentication buttons
     */
    private fun setupButtons() {
        binding.btnAuth.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                showSnackbar(getString(R.string.error_empty_fields))
                return@setOnClickListener
            }

            if (isLoginMode) {
                // LOGIN MODE
                viewModel.login(email, password)
            } else {
                // REGISTER MODE
                val name = binding.etName.text.toString().trim()
                val phone = binding.etPhone.text.toString().trim()
                val company = binding.etCompany.text.toString().trim()
                
                if (name.isEmpty() || phone.isEmpty()) {
                    showSnackbar(getString(R.string.error_empty_fields))
                    return@setOnClickListener
                }
                
                viewModel.register(
                    name = name,
                    email = email,
                    phone = phone,
                    password = password,
                    role = selectedRole,
                    company = company,
                    language = selectedLanguage
                )
            }
        }

        // Forgot password button
        binding.tvForgotPassword.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isEmpty()) {
                showSnackbar(getString(R.string.enter_email_for_reset))
            } else {
                viewModel.resetPassword(email)
            }
        }
    }

    /**
     * Observe authentication state changes
     */
    private fun observeViewModel() {
        viewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnAuth.isEnabled = false
                }
                is AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    navigateToMain()
                }
                is AuthState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAuth.isEnabled = true
                    showSnackbar(state.message)
                }
                is AuthState.PasswordResetSent -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackbar(getString(R.string.password_reset_sent))
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAuth.isEnabled = true
                }
            }
        }
    }

    /**
     * Navigate to MainActivity with proper flags
     */
    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    /**
     * Show snackbar message
     */
    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }
}
