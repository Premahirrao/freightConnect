package com.freightconnect.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freightconnect.model.*
import com.freightconnect.repository.FreightRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

// ═══════════════════════════════════════════════════════════════════════════
// MainViewModel
// ═══════════════════════════════════════════════════════════════════════════

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
            } catch (e: Exception) {
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

class FleetHomeViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _myRoutes = MutableLiveData<List<TruckRoute>>()
    val myRoutes: LiveData<List<TruckRoute>> = _myRoutes

    private val _receivedInterests = MutableLiveData<List<BookingInterest>>()
    val receivedInterests: LiveData<List<BookingInterest>> = _receivedInterests

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadMyRoutes() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _myRoutes.value = repo.getFleetOwnerRoutes(repo.currentUid())
            } catch (e: Exception) {
                _myRoutes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadReceivedInterests() {
        viewModelScope.launch {
            try {
                _receivedInterests.value = repo.getReceivedInterests(repo.currentUid())
            } catch (e: Exception) {
                _receivedInterests.value = emptyList()
            }
        }
    }

    init {
        loadMyRoutes()
        loadReceivedInterests()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// BusinessHomeViewModel (Business Owner dashboard)
// ═══════════════════════════════════════════════════════════════════════════

class BusinessHomeViewModel : ViewModel() {

    private val repo = FreightRepository()

    private val _myCargos = MutableLiveData<List<CargoRequest>>()
    val myCargos: LiveData<List<CargoRequest>> = _myCargos

    private val _receivedInterests = MutableLiveData<List<BookingInterest>>()
    val receivedInterests: LiveData<List<BookingInterest>> = _receivedInterests

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadMyCargos() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _myCargos.value = repo.getBusinessOwnerCargos(repo.currentUid())
            } catch (e: Exception) {
                _myCargos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadReceivedInterests() {
        viewModelScope.launch {
            try {
                _receivedInterests.value = repo.getReceivedInterests(repo.currentUid())
            } catch (e: Exception) {
                _receivedInterests.value = emptyList()
            }
        }
    }

    init {
        loadMyCargos()
        loadReceivedInterests()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// SearchRoutesViewModel (Business Owner searches available routes)
// ═══════════════════════════════════════════════════════════════════════════

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
            } catch (e: Exception) {
                _routes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun filterByVehicleType(type: VehicleType) {
        val current = _routes.value ?: return
        _routes.value = current.filter { it.vehicleType == type }
    }

    init {
        searchRoutes()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// SearchCargosViewModel (Fleet Owner searches cargo requests)
// ═══════════════════════════════════════════════════════════════════════════

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
            } catch (e: Exception) {
                _cargos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun filterByGoodsType(type: GoodsType) {
        val current = _cargos.value ?: return
        _cargos.value = current.filter { it.goodsType == type }
    }

    init {
        searchCargos()
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// PostRouteViewModel
// ═══════════════════════════════════════════════════════════════════════════

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
