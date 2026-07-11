package com.freightconnect.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.freightconnect.R
import com.freightconnect.databinding.ActivityAuthBinding
import com.freightconnect.model.AppLanguage
import com.freightconnect.model.UserRole
import com.freightconnect.utils.LanguageHelper
import com.freightconnect.viewmodel.AuthState
import com.freightconnect.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

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
    private val firebaseAuth = FirebaseAuth.getInstance()

    private var isLoginMode = true
    private var selectedRole = UserRole.BUSINESS_OWNER
    private var selectedLanguage = "en"

    private lateinit var googleSignInClient: com.google.android.gms.auth.api.signin.GoogleSignInClient
    private var phoneVerificationId: String? = null
    private var phoneResendToken: PhoneAuthProvider.ForceResendingToken? = null

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode != RESULT_OK || data == null) {
            showSnackbar(getString(R.string.google_login_failed))
            return@registerForActivityResult
        }

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken.isNullOrBlank()) {
                showSnackbar(getString(R.string.invalid_google_token))
                return@registerForActivityResult
            }
            viewModel.signInWithGoogle(idToken, selectedRole, selectedLanguage)
        } catch (e: ApiException) {
            showSnackbar(getString(R.string.google_login_failed))
        }
    }

    private val phoneCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            viewModel.signInWithPhone(credential, selectedRole, selectedLanguage)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            showSnackbar(e.message ?: getString(R.string.phone_login_failed))
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            phoneVerificationId = verificationId
            phoneResendToken = token
            showSnackbar(getString(R.string.phone_code_sent))
            showOtpDialog()
        }
    }

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
        setupAuthModeSwitcher()
        setupRoleToggle()
        setupGoogleSignIn()
        setupButtons()
        observeViewModel()
    }

    private fun setupGoogleSignIn() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, options)
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

    private fun setupAuthModeSwitcher() {
        updateAuthModeUi()
        binding.tvAuthModeAction.setOnClickListener {
            isLoginMode = !isLoginMode
            updateAuthModeUi()
        }
    }

    private fun updateAuthModeUi() {
        val registerVisibility = if (isLoginMode) View.GONE else View.VISIBLE

        binding.layoutName.visibility = registerVisibility
        binding.layoutPhone.visibility = registerVisibility
        binding.layoutCompany.visibility = registerVisibility
        binding.layoutRoleSelector.visibility = registerVisibility
        binding.tvForgotPassword.visibility = if (isLoginMode) View.VISIBLE else View.GONE

        binding.btnAuth.text = getString(if (isLoginMode) R.string.login else R.string.register)
        binding.tvAuthModePrompt.text = getString(
            if (isLoginMode) R.string.dont_have_account else R.string.already_have_account
        )
        binding.tvAuthModeAction.text = getString(
            if (isLoginMode) R.string.register else R.string.login
        )
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

        binding.btnGoogleSignIn.setOnClickListener {
            googleSignInLauncher.launch(googleSignInClient.signInIntent)
        }

        binding.btnPhoneSignIn.setOnClickListener {
            showPhoneNumberDialog()
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
                    binding.btnGoogleSignIn.isEnabled = false
                    binding.btnPhoneSignIn.isEnabled = false
                }
                is AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    navigateToMain()
                }
                is AuthState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAuth.isEnabled = true
                    binding.btnGoogleSignIn.isEnabled = true
                    binding.btnPhoneSignIn.isEnabled = true
                    showSnackbar(state.message)
                }
                is AuthState.PasswordResetSent -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnGoogleSignIn.isEnabled = true
                    binding.btnPhoneSignIn.isEnabled = true
                    showSnackbar(getString(R.string.password_reset_sent))
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAuth.isEnabled = true
                    binding.btnGoogleSignIn.isEnabled = true
                    binding.btnPhoneSignIn.isEnabled = true
                }
            }
        }
    }

    private fun showPhoneNumberDialog() {
        val inputLayout = TextInputLayout(this).apply {
            hint = getString(R.string.enter_phone_number)
        }
        val input = TextInputEditText(this).apply {
            inputType = InputType.TYPE_CLASS_PHONE
        }
        inputLayout.addView(input)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.continue_with_phone)
            .setView(inputLayout)
            .setPositiveButton(R.string.send_code) { _, _ ->
                val phoneNumber = input.text?.toString()?.trim().orEmpty()
                if (phoneNumber.isBlank()) {
                    showSnackbar(getString(R.string.invalid_phone_number))
                } else {
                    startPhoneVerification(phoneNumber)
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun showOtpDialog() {
        val verificationId = phoneVerificationId ?: return
        val inputLayout = TextInputLayout(this).apply {
            hint = getString(R.string.enter_verification_code)
        }
        val input = TextInputEditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        inputLayout.addView(input)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.verify)
            .setView(inputLayout)
            .setPositiveButton(R.string.verify) { _, _ ->
                val code = input.text?.toString()?.trim().orEmpty()
                if (code.isBlank()) {
                    showSnackbar(getString(R.string.invalid_verification_code))
                } else {
                    val credential = PhoneAuthProvider.getCredential(verificationId, code)
                    viewModel.signInWithPhone(credential, selectedRole, selectedLanguage)
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun startPhoneVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(phoneCallbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
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
