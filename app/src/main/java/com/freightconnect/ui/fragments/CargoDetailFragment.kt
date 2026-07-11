package com.freightconnect.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.freightconnect.R
import com.freightconnect.databinding.FragmentCargoDetailBinding
import com.freightconnect.model.BookingInterest
import com.freightconnect.model.CargoStatus
import com.freightconnect.model.UserRole
import com.freightconnect.repository.FreightRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CargoDetailFragment : Fragment() {

    private var _binding: FragmentCargoDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CargoDetailFragmentArgs by navArgs()
    private val repo = FreightRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCargoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCargoDetails()
    }

    private fun loadCargoDetails() {
        lifecycleScope.launch {
            try {
                val cargo = repo.getCargoRequest(args.cargoId)
                val currentUser = repo.getUser(repo.currentUid())
                
                if (cargo == null) {
                    showSnackbar(getString(R.string.error_loading))
                    return@launch
                }

                binding.apply {
                    tvGoodsType.text = cargo.goodsType.displayNameEn
                    tvDescription.text = cargo.goodsDescription
                    tvWeight.text = "${cargo.weightTons} ${getString(R.string.tons)}"
                    tvPickupCity.text = cargo.pickupCity
                    tvDeliveryCity.text = cargo.deliveryCity
                    tvPickupAddress.text = cargo.pickupAddress
                    tvDeliveryAddress.text = cargo.deliveryAddress
                    tvRequiredVehicle.text = cargo.requiredVehicleType.displayNameEn
                    tvPickupDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        .format(Date(cargo.pickupDate))
                    tvBudget.text = "₹${cargo.budgetAmount.toLong()}"
                    tvStatus.text = cargo.status.name
                    tvSpecialReq.text = cargo.specialRequirements.ifBlank { "None" }
                    tvBusinessOwner.text = cargo.businessOwnerName
                    tvBusinessCompany.text = cargo.businessOwnerCompany
                }

                // Show actions based on user role
                when (currentUser?.role) {
                    UserRole.FLEET_OWNER -> {
                        val canSendInterest = cargo.status in listOf(CargoStatus.OPEN, CargoStatus.PENDING)
                        binding.btnSendInterest.visibility = if (canSendInterest) View.VISIBLE else View.GONE
                        if (canSendInterest) {
                            binding.btnSendInterest.setOnClickListener {
                                showSendInterestDialog(cargo.cargoId)
                            }
                        }
                    }
                    UserRole.BUSINESS_OWNER -> {
                        if (cargo.businessOwnerUid == currentUser.uid && cargo.assignedFleetOwnerUid.isNotBlank()) {
                            binding.btnCall.visibility = View.VISIBLE
                            binding.btnCall.setOnClickListener {
                                dialPhone(cargo.businessOwnerPhone)
                            }
                        } else {
                            binding.btnCall.visibility = View.GONE
                        }

                        val canCancelCargo = cargo.businessOwnerUid == currentUser.uid &&
                            cargo.status in listOf(CargoStatus.OPEN, CargoStatus.PENDING)
                        binding.btnCancelCargo.visibility = if (canCancelCargo) View.VISIBLE else View.GONE
                        if (canCancelCargo) {
                            binding.btnCancelCargo.setOnClickListener {
                                showCancelCargoDialog(cargo.cargoId)
                            }
                        }
                    }
                    else -> {}
                }

            } catch (e: Exception) {
                showSnackbar(getString(R.string.error_loading))
            }
        }
    }

    private fun showSendInterestDialog(cargoId: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_send_interest, null)
        val etMessage = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etMessage)
        val etOfferedPrice = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etOfferedPrice)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.send_interest_title)
            .setView(dialogView)
            .setPositiveButton(R.string.send_interest) { _, _ ->
                val message = etMessage.text.toString()
                val price = etOfferedPrice.text.toString().toDoubleOrNull() ?: 0.0
                sendInterest(cargoId, message, price)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun sendInterest(cargoId: String, message: String, offeredPrice: Double) {
        lifecycleScope.launch {
            try {
                val currentUser = repo.getUser(repo.currentUid())
                val cargo = repo.getCargoRequest(cargoId)
                
                if (currentUser == null || cargo == null) return@launch

                val interest = BookingInterest(
                    initiatorUid = currentUser.uid,
                    initiatorName = currentUser.name,
                    initiatorPhone = currentUser.phone,
                    initiatorRole = UserRole.FLEET_OWNER,
                    targetUid = cargo.businessOwnerUid,
                    targetName = cargo.businessOwnerName,
                    targetRole = UserRole.BUSINESS_OWNER,
                    cargoId = cargoId,
                    message = message,
                    offeredPrice = offeredPrice,
                    goodsWeightTons = cargo.weightTons
                )

                repo.sendInterest(interest)
                showSnackbar("${getString(R.string.interest_sent)} (Weight: ${cargo.weightTons}T)")
            } catch (e: Exception) {
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun dialPhone(phone: String) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    private fun showCancelCargoDialog(cargoId: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.cancel_cargo_confirm_title)
            .setMessage(R.string.cancel_cargo_confirm_message)
            .setPositiveButton(R.string.cancel_cargo) { _, _ ->
                cancelCargo(cargoId)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun cancelCargo(cargoId: String) {
        lifecycleScope.launch {
            try {
                val cargo = repo.getCargoRequest(cargoId)
                if (cargo == null) {
                    showSnackbar(getString(R.string.error_loading))
                    return@launch
                }
                val canCancel = cargo.status in listOf(CargoStatus.OPEN, CargoStatus.PENDING)
                if (!canCancel) {
                    showSnackbar(getString(R.string.cancel_not_allowed))
                    return@launch
                }
                repo.cancelCargo(cargoId)
                showSnackbar(getString(R.string.cargo_cancelled))
                loadCargoDetails()
            } catch (e: Exception) {
                showSnackbar(getString(R.string.error_loading))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
