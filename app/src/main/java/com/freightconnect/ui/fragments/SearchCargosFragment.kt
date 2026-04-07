package com.freightconnect.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentSearchCargosBinding
import com.freightconnect.ui.adapters.CargoAdapter
import com.freightconnect.viewmodel.SearchCargosViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * SearchCargosFragment - Fleet Owner searches available cargo requests
 *
 * Features:
 * - Request location permission and auto-detect user's current city location
 * - Search cargos by from/to cities
 * - Real-time search as user types
 * - Clear filters button
 * - Manual search button
 * - Swipe-to-refresh
 *
 * Flow:
 * - Request location permission → Detect city → Auto-populate from city → Search results
 * - Click on cargo → CargoDetailFragment (view details, send interest)
 */
class SearchCargosFragment : Fragment() {

    private var _binding: FragmentSearchCargosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchCargosViewModel by viewModels()
    private lateinit var cargoAdapter: CargoAdapter
    
    // Location permission launcher
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                // Fine location permission granted
                detectAndSetUserCity()
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                // Coarse location permission granted
                detectAndSetUserCity()
            }
            else -> {
                // Permission denied
                Snackbar.make(
                    binding.root,
                    "Location permission denied. Please enable it in app settings to auto-detect your city.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchCargosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeData()
    }

    /**
     * Setup cargos recycler view
     */
    private fun setupRecyclerView() {
        cargoAdapter = CargoAdapter(
            onCargoClick = { cargo ->
                val action = SearchCargosFragmentDirections.actionSearchToCargoDetail(cargo.cargoId)
                findNavController().navigate(action)
            }
        )
        
        binding.rvCargos.apply {
            adapter = cargoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            performSearch()
        }
    }

    /**
     * Setup search input fields and buttons
     * Request location permission and auto-detect user's current location city
     */
    private fun setupSearch() {
        // Request location permission and detect current user location
        requestLocationPermissionAndDetectCity()

        // Real-time search as user types
        binding.etFromCity.addTextChangedListener {
            performSearch()
        }

        binding.etToCity.addTextChangedListener {
            performSearch()
        }

        // Manual search button
        binding.btnSearch.setOnClickListener {
            performSearch()
        }

        // Clear filters
        binding.btnClearFilters.setOnClickListener {
            binding.etFromCity.text?.clear()
            binding.etToCity.text?.clear()
            performSearch()
        }
    }

    /**
     * Request location permission and detect user's city if permission is granted
     */
    private fun requestLocationPermissionAndDetectCity() {
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationPermission = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fineLocationPermission || coarseLocationPermission) {
            // Permissions already granted
            detectAndSetUserCity()
        } else {
            // Request permissions
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    /**
     * Detect user's current city location using device GPS
     * Auto-populate the from city field with user's current location and search
     */
    private fun detectAndSetUserCity() {
        lifecycleScope.launch {
            try {
                val city = getUserCityLocation()
                if (!city.isNullOrBlank()) {
                    binding.etFromCity.setText(city)
                    performSearch()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Get user's current city from device location using Geocoder
     * Converts GPS coordinates to city name
     */
    private suspend fun getUserCityLocation(): String? {
        return withContext(Dispatchers.Default) {
            try {
                val fusedLocationClient = LocationServices
                    .getFusedLocationProviderClient(requireContext())
                
                @Suppress("MissingPermission")
                val location = fusedLocationClient.lastLocation.await()
                
                location?.let {
                    val geocoder = Geocoder(requireContext())
                    val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val result = mutableListOf<Address>()
                        geocoder.getFromLocation(it.latitude, it.longitude, 1) { addrs ->
                            result.addAll(addrs)
                        }
                        result
                    } else {
                        @Suppress("DEPRECATION")
                        geocoder.getFromLocation(it.latitude, it.longitude, 1) ?: emptyList()
                    }
                    
                    if (addresses.isNotEmpty()) {
                        addresses[0].locality ?: addresses[0].adminArea
                    } else {
                        null
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /**
     * Extension to make Location Task awaitable for coroutines
     */
    private suspend fun <T> Task<T>.await(): T {
        return suspendCancellableCoroutine { continuation ->
            addOnSuccessListener { continuation.resume(it) }
            addOnFailureListener { continuation.resumeWithException(it) }
            addOnCanceledListener { continuation.cancel() }
        }
    }

    /**
     * Perform search with current filter values
     */
    private fun performSearch() {
        val fromCity = binding.etFromCity.text.toString().trim()
        val toCity = binding.etToCity.text.toString().trim()
        viewModel.searchCargos(fromCity, toCity)
    }

    /**
     * Observe search results and loading state
     */
    private fun observeData() {
        viewModel.cargos.observe(viewLifecycleOwner) { cargos ->
            binding.swipeRefresh.isRefreshing = false
            
            if (cargos.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvCargos.visibility = View.GONE
                binding.tvEmptyMessage.text = getString(R.string.no_cargo_found)
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvCargos.visibility = View.VISIBLE
                cargoAdapter.submitList(cargos)
                binding.tvResultCount.text = resources.getQuantityString(
                    R.plurals.cargos_found, cargos.size, cargos.size
                )
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.shimmerLayout.visibility = View.VISIBLE
                binding.shimmerLayout.startShimmer()
                binding.rvCargos.visibility = View.GONE
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
