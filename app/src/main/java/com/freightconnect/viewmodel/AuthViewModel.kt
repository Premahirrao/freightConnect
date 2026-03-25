package com.freightconnect.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freightconnect.model.FleetOwnerProfile
import com.freightconnect.model.User
import com.freightconnect.model.UserRole
import com.freightconnect.repository.FreightRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    object PasswordResetSent : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val repo = FreightRepository()

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    fun isUserLoggedIn() = auth.currentUser != null

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _authState.value = AuthState.Success
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        role: UserRole,
        company: String,
        language: String
    ) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid ?: throw Exception("UID is null")
                
                val user = User(
                    uid = uid,
                    name = name,
                    phone = phone,
                    email = email,
                    role = role,
                    companyName = company,
                    preferredLanguage = language
                )
                repo.createUser(user)
                
                // Create fleet profile if fleet owner
                if (role == UserRole.FLEET_OWNER) {
                    val profile = FleetOwnerProfile(
                        uid = uid,
                        name = name,
                        phone = phone,
                        companyName = company
                    )
                    repo.createOrUpdateFleetProfile(profile)
                }
                
                _authState.value = AuthState.Success
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun resetPassword(email: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email).await()
                _authState.value = AuthState.PasswordResetSent
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Reset failed")
            }
        }
    }
}
