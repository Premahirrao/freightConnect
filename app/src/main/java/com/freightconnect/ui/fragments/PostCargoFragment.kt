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
import com.freightconnect.databinding.FragmentPostCargoBinding
import com.freightconnect.model.CargoRequest
import com.freightconnect.model.GoodsType
import com.freightconnect.model.VehicleType
import com.freightconnect.repository.FreightRepository
import com.freightconnect.viewmodel.PostCargoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PostCargoFragment : Fragment() {

    private var _binding: FragmentPostCargoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostCargoViewModel by viewModels()
    private val repo = FreightRepository()

    private var selectedPickupDate: Long = 0L
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPostCargoBinding.inflate(inflater, container, false)
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
        val goodsTypes = GoodsType.entries.map { it.displayNameEn }
        binding.actvGoodsType.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, goodsTypes)
        )

        val vehicleTypes = VehicleType.entries.map { it.displayNameEn }
        binding.actvRequiredVehicle.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, vehicleTypes)
        )
    }

    private fun setupDatePicker() {
        binding.etPickupDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedPickupDate = calendar.timeInMillis
                    binding.etPickupDate.setText(
                        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(selectedPickupDate))
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

                val goodsTypeIndex = GoodsType.entries.indexOfFirst {
                    it.displayNameEn == binding.actvGoodsType.text.toString()
                }.takeIf { it >= 0 } ?: 0

                val vehicleTypeIndex = VehicleType.entries.indexOfFirst {
                    it.displayNameEn == binding.actvRequiredVehicle.text.toString()
                }.takeIf { it >= 0 } ?: 0

                val cargo = CargoRequest(
                    businessOwnerUid = user.uid,
                    businessOwnerName = user.name,
                    businessOwnerPhone = user.phone,
                    businessOwnerCompany = user.companyName,
                    goodsType = GoodsType.entries[goodsTypeIndex],
                    goodsDescription = binding.etDescription.text.toString().trim(),
                    weightTons = binding.etWeight.text.toString().toFloatOrNull() ?: 0f,
                    pickupCity = binding.etPickupCity.text.toString().trim(),
                    pickupAddress = binding.etPickupAddress.text.toString().trim(),
                    deliveryCity = binding.etDeliveryCity.text.toString().trim(),
                    deliveryAddress = binding.etDeliveryAddress.text.toString().trim(),
                    requiredVehicleType = VehicleType.entries[vehicleTypeIndex],
                    pickupDate = selectedPickupDate,
                    budgetAmount = binding.etBudget.text.toString().toDoubleOrNull() ?: 0.0,
                    specialRequirements = binding.etSpecialReq.text.toString().trim()
                )

                viewModel.postCargo(cargo)
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        if (binding.actvGoodsType.text.isNullOrBlank()) {
            binding.tilGoodsType.error = getString(R.string.required_field)
            valid = false
        } else binding.tilGoodsType.error = null

        if (binding.etWeight.text.isNullOrBlank()) {
            binding.tilWeight.error = getString(R.string.required_field)
            valid = false
        } else binding.tilWeight.error = null

        if (binding.etPickupCity.text.isNullOrBlank()) {
            binding.tilPickupCity.error = getString(R.string.required_field)
            valid = false
        } else binding.tilPickupCity.error = null

        if (binding.etDeliveryCity.text.isNullOrBlank()) {
            binding.tilDeliveryCity.error = getString(R.string.required_field)
            valid = false
        } else binding.tilDeliveryCity.error = null

        if (selectedPickupDate == 0L) {
            binding.tilPickupDate.error = getString(R.string.required_field)
            valid = false
        } else binding.tilPickupDate.error = null

        return valid
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            binding.btnSubmit.isEnabled = !loading
        }

        viewModel.postSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                showSnackbar(getString(R.string.post_cargo_success))
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
