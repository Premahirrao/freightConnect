package com.freightconnect.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freightconnect.model.*
import com.freightconnect.repository.FreightRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch

// ═══════════════════════════════════════════════════════════════════════════
// VIEWMODEL ARCHITECTURE OVERVIEW
// ═══════════════════════════════════════════════════════════════════════════
//
// This file contains all shared ViewModels for the FreightConnect app.
//
// Flow: Activity/Fragment → ViewModel → Repository → Firestore
//
// Key Principles:
// 1. Each ViewModel is responsible for a specific screen/feature
// 2. ViewModels manage UI state via LiveData observables
// 3. Repository handles all data operations (Firestore)
// 4. Listeners are cleaned up in onCleared() to prevent memory leaks
//
// ═══════════════════════════════════════════════════════════════════════════

/**
 * MainViewModel - Manages overall app state
 *
 * Responsibilities:
 * - Load currently logged-in user
 * - Maintain user session state
 * - Handle logout
 *
 * Used by: MainActivity
 */
class MainViewModel : ViewModel() {

    private val repo = FreightRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            _currentUser.value = null
            return
        }
        viewModelScope.launch {
            try {
                _currentUser.value = repo.getUser(uid)
            } catch (_: Exception) {
                _currentUser.value = null
            }
        }
    }

    fun logout() {
        auth.signOut()
        _currentUser.value = null
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// FleetHomeViewModel (Fleet Owner dashboard)
// ═══════════════════════════════════════════════════════════════════════════

/**
 * FleetHomeViewModel - Fleet Owner's dashboard and route management
 *
 * Features:
 * - Real-time vehicle tracking
 * - Route management (my posted routes)
 * - Interest management (incoming booking requests)
 * - Dashboard metrics (total vehicles, completed trips, routes, pending interests)
 *
 * Real-time Listeners:
 * - vehiclesListener: Tracks fleet vehicles
 * - routesListener: Tracks posted routes
 * - interestsListener: Tracks incoming booking interests
 *
 * Used by: FleetHomeFragment
 */
class FleetHomeViewModel : ViewModel() {

    private val repo = FreightRepository()
    private val currentUid = repo.currentUid()

    // Core data
    private val _myRoutes = MutableLiveData<List<TruckRoute>>()
    val myRoutes: LiveData<List<TruckRoute>> = _myRoutes

    private val _receivedInterests = MutableLiveData<List<BookingInterest>>()
    val receivedInterests: LiveData<List<BookingInterest>> = _receivedInterests

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // Dashboard metrics
    private val _completedTrips = MutableLiveData(0)
    val completedTrips: LiveData<Int> = _completedTrips

    private val _totalPostedRoutes = MutableLiveData(0)
    val totalPostedRoutes: LiveData<Int> = _totalPostedRoutes


    // Listener registrations for cleanup
    private var routesListener: ListenerRegistration? = null
    private var interestsListener: ListenerRegistration? = null

    fun loadDashboardData() {
        _isLoading.value = true
        
        // Setup real-time listeners
        routesListener?.remove()
        interestsListener?.remove()

        routesListener = repo.listenToFleetRoutes(
            currentUid,
            onUpdate = { routes ->
                _myRoutes.value = routes
                updateRouteMetrics(routes)
                _isLoading.value = false
            },
            onError = {
                _isLoading.value = false
            }
        )

        interestsListener = repo.listenToFleetInterests(
            currentUid,
            onUpdate = { interests ->
                _receivedInterests.value = interests
            },
            onError = {
                // Handle error
            }
        )
    }

    fun refreshData() {
        loadDashboardData()
    }

    private fun updateRouteMetrics(routes: List<TruckRoute>) {
        _totalPostedRoutes.value = routes.size
        
        // Count completed trips
        val completed = routes.count { it.status == RouteStatus.COMPLETED }
        _completedTrips.value = completed
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up listeners to prevent memory leaks
        routesListener?.remove()
        interestsListener?.remove()
    }

    init {
        loadDashboardData()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// BusinessHomeViewModel (Business Owner dashboard)
// ═══════════════════════════════════════════════════════════════════════════

/**
 * BusinessHomeViewModel - Business Owner's cargo management and dashboard
 *
 * Features:
 * - Cargo request management (my posted cargos)
 * - Interest management (incoming booking requests from fleet owners)
 * - Dashboard metrics (total cargo, open, pending, booked)
 * - Real-time updates via Firestore listeners
 *
 * Real-time Listeners:
 * - cargosListener: Tracks posted cargo requests
 * - interestsListener: Tracks incoming booking interests
 *
 * Used by: BusinessHomeFragment
 */
class BusinessHomeViewModel : ViewModel() {

    private val repo = FreightRepository()
    private val currentUid = repo.currentUid()

    // Core data
    private val _myCargos = MutableLiveData<List<CargoRequest>>()
    val myCargos: LiveData<List<CargoRequest>> = _myCargos

    private val _receivedInterests = MutableLiveData<List<BookingInterest>>()
    val receivedInterests: LiveData<List<BookingInterest>> = _receivedInterests

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // Dashboard metrics
    private val _totalCargos = MutableLiveData(0)
    val totalCargos: LiveData<Int> = _totalCargos

    private val _openCargos = MutableLiveData(0)
    val openCargos: LiveData<Int> = _openCargos

    private val _pendingCargos = MutableLiveData(0)
    val pendingCargos: LiveData<Int> = _pendingCargos

    private val _bookedCargos = MutableLiveData(0)
    val bookedCargos: LiveData<Int> = _bookedCargos

    // Listener registrations for cleanup
    private var cargosListener: ListenerRegistration? = null
    private var interestsListener: ListenerRegistration? = null

    fun loadDashboardData() {
        _isLoading.value = true
        
        // Setup real-time listeners
        cargosListener?.remove()
        interestsListener?.remove()

        cargosListener = repo.listenToBusinessCargos(
            currentUid,
            onUpdate = { cargos ->
                _myCargos.value = cargos
                updateCargoMetrics(cargos)
                _isLoading.value = false
            },
            onError = {
                _isLoading.value = false
            }
        )

        interestsListener = repo.listenToBusinessInterests(
            currentUid,
            onUpdate = { interests ->
                _receivedInterests.value = interests
            },
            onError = {
                // Handle error
            }
        )
    }

    fun refreshData() {
        loadDashboardData()
    }

    private fun updateCargoMetrics(cargos: List<CargoRequest>) {
        // Total cargos
        _totalCargos.value = cargos.size

        // Status breakdown
        _openCargos.value = cargos.count { it.status == CargoStatus.OPEN }
        _pendingCargos.value = cargos.count { it.status == CargoStatus.PENDING }
        _bookedCargos.value = cargos.count { it.status == CargoStatus.BOOKED }
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up listeners to prevent memory leaks
        cargosListener?.remove()
        interestsListener?.remove()
    }

    init {
        loadDashboardData()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// SearchRoutesViewModel (Business Owner searches available routes)
// ═══════════════════════════════════════════════════════════════════════════

/**
 * SearchRoutesViewModel - Search and filter available truck routes
 *
 * Features:
 * - Search routes by from/to cities
 * - Filter by vehicle type
 * - Real-time search as user types
 *
 * Used by: SearchRoutesFragment
 */
class SearchRoutesViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _routes = MutableLiveData<List<TruckRoute>>()
    val routes: LiveData<List<TruckRoute>> = _routes

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchRoutes(fromCity: String = "", toCity: String = "") {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _routes.value = repo.searchRoutesByCity(fromCity, toCity)
            } catch (_: Exception) {
                _routes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }


    init {
        searchRoutes()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// SearchCargosViewModel (Fleet Owner searches cargo requests)
// ═══════════════════════════════════════════════════════════════════════════

/**
 * SearchCargosViewModel - Search and filter cargo requests
 *
 * Features:
 * - Search cargos by from/to cities
 * - Filter by goods type
 * - Real-time search as user types
 *
 * Used by: SearchCargosFragment
 */
class SearchCargosViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _cargos = MutableLiveData<List<CargoRequest>>()
    val cargos: LiveData<List<CargoRequest>> = _cargos

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchCargos(fromCity: String = "", toCity: String = "") {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _cargos.value = repo.searchCargosByCity(fromCity, toCity)
            } catch (_: Exception) {
                _cargos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }


    init {
        searchCargos()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// PostRouteViewModel
// ═══════════════════════════════════════════════════════════════════════════

/**
 * PostRouteViewModel - Handle posting new truck routes
 *
 * Flow:
 * 1. User fills route details
 * 2. PostRouteFragment calls postRoute()
 * 3. Repository saves to Firestore
 * 4. ViewModel emits success/error state
 * 5. Fragment navigates back on success
 *
 * Used by: PostRouteFragment
 */
class PostRouteViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _postSuccess = MutableLiveData<Boolean>()
    val postSuccess: LiveData<Boolean> = _postSuccess

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun postRoute(route: TruckRoute) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                repo.postTruckRoute(route)
                _postSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// PostCargoViewModel
// ═══════════════════════════════════════════════════════════════════════════

/**
 * PostCargoViewModel - Handle posting new cargo requests
 *
 * Flow:
 * 1. User fills cargo details
 * 2. PostCargoFragment calls postCargo()
 * 3. Repository saves to Firestore
 * 4. ViewModel emits success/error state
 * 5. Fragment navigates back on success
 *
 * Used by: PostCargoFragment
 */
class PostCargoViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _postSuccess = MutableLiveData<Boolean>()
    val postSuccess: LiveData<Boolean> = _postSuccess

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun postCargo(cargo: CargoRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                repo.postCargoRequest(cargo)
                _postSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
