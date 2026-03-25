package com.freightconnect.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.freightconnect.R
import com.freightconnect.databinding.FragmentPostRouteBinding
import com.freightconnect.model.TruckRoute
import com.freightconnect.model.VehicleType
import com.freightconnect.repository.FreightRepository
import com.freightconnect.viewmodel.PostRouteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PostRouteFragment : Fragment() {

    private var _binding: FragmentPostRouteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostRouteViewModel by viewModels()
    private val repo = FreightRepository()

    private var selectedDepartureDate: Long = 0L
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPostRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDropdowns()
        setupDatePicker()
        setupSubmit()
        observeViewModel()
    }

    private fun setupDropdowns() {
        val vehicleTypes = VehicleType.values().map { it.displayNameEn }
        binding.actvVehicleType.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, vehicleTypes)
        )
    }

    private fun setupDatePicker() {
        binding.etDepartureDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedDepartureDate = calendar.timeInMillis
                    binding.etDepartureDate.setText(
                        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(selectedDepartureDate))
                    )
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = System.currentTimeMillis()
            }.show()
        }
    }

    private fun setupSubmit() {
        binding.btnSubmit.setOnClickListener {
            if (!validateForm()) return@setOnClickListener

            lifecycleScope.launch {
                val user = repo.getUser(repo.currentUid())
                if (user == null) {
                    showSnackbar(getString(R.string.error_loading))
                    return@launch
                }

                val vehicleTypeIndex = VehicleType.values().indexOfFirst {
                    it.displayNameEn == binding.actvVehicleType.text.toString()
                }.takeIf { it >= 0 } ?: 0

                val route = TruckRoute(
                    fleetOwnerUid = user.uid,
                    fleetOwnerName = user.name,
                    fleetOwnerPhone = user.phone,
                    fleetOwnerCompany = user.companyName,
                    fromCity = binding.etFromCity.text.toString().trim(),
                    fromAddress = binding.etFromAddress.text.toString().trim(),
                    toCity = binding.etToCity.text.toString().trim(),
                    toAddress = binding.etToAddress.text.toString().trim(),
                    vehicleType = VehicleType.values()[vehicleTypeIndex],
                    vehicleNumber = binding.etVehicleNumber.text.toString().trim(),
                    availableCapacityTons = binding.etCapacity.text.toString().toFloatOrNull() ?: 0f,
                    pricePerTon = binding.etPricePerTon.text.toString().toDoubleOrNull() ?: 0.0,
                    departureDate = selectedDepartureDate,
                    notes = binding.etNotes.text.toString().trim(),
                    acceptsPartialLoad = binding.switchPartialLoad.isChecked
                )

                viewModel.postRoute(route)
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        if (binding.etFromCity.text.isNullOrBlank()) {
            binding.tilFromCity.error = getString(R.string.required_field)
            valid = false
        } else binding.tilFromCity.error = null

        if (binding.etToCity.text.isNullOrBlank()) {
            binding.tilToCity.error = getString(R.string.required_field)
            valid = false
        } else binding.tilToCity.error = null

        if (binding.actvVehicleType.text.isNullOrBlank()) {
            binding.tilVehicleType.error = getString(R.string.required_field)
            valid = false
        } else binding.tilVehicleType.error = null

        if (binding.etVehicleNumber.text.isNullOrBlank()) {
            binding.tilVehicleNumber.error = getString(R.string.required_field)
            valid = false
        } else binding.tilVehicleNumber.error = null

        if (binding.etCapacity.text.isNullOrBlank()) {
            binding.tilCapacity.error = getString(R.string.required_field)
            valid = false
        } else binding.tilCapacity.error = null

        if (selectedDepartureDate == 0L) {
            binding.tilDepartureDate.error = getString(R.string.required_field)
            valid = false
        } else binding.tilDepartureDate.error = null

        return valid
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            binding.btnSubmit.isEnabled = !loading
        }

        viewModel.postSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                showSnackbar(getString(R.string.post_route_success))
                findNavController().popBackStack()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { msg ->
            if (msg != null) showSnackbar(msg)
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
