package com.freightconnect.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.freightconnect.R
import com.freightconnect.databinding.FragmentRouteDetailBinding
import com.freightconnect.model.BookingInterest
import com.freightconnect.model.RouteStatus
import com.freightconnect.model.UserRole
import com.freightconnect.repository.FreightRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RouteDetailFragment : Fragment() {

    private var _binding: FragmentRouteDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RouteDetailFragmentArgs by navArgs()
    private val repo = FreightRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRouteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRouteDetails()
    }

    private fun loadRouteDetails() {
        lifecycleScope.launch {
            try {
                val route = repo.getTruckRoute(args.routeId)
                val currentUser = repo.getUser(repo.currentUid())
                
                if (route == null) {
                    showSnackbar(getString(R.string.error_loading))
                    return@launch
                }

                binding.apply {
                    tvFromCity.text = route.fromCity
                    tvToCity.text = route.toCity
                    tvFromAddress.text = route.fromAddress
                    tvToAddress.text = route.toAddress
                    tvVehicleType.text = route.vehicleType.displayNameEn
                    tvVehicleNumber.text = route.vehicleNumber
                    tvCapacity.text = "${route.availableCapacityTons} ${getString(R.string.tons)} (${route.remainingCapacityTons} ${getString(R.string.tons)} remaining)"
                    tvPricePerTon.text = "₹${route.pricePerTon.toLong()}/ton"
                    tvDepartureDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        .format(Date(route.departureDate))
                    tvStatus.text = route.status.name
                    tvNotes.text = route.notes.ifBlank { "No additional notes" }
                    tvPartialLoad.text = if (route.acceptsPartialLoad) "Yes" else "No"
                    tvFleetOwner.text = route.fleetOwnerName
                    tvFleetCompany.text = route.fleetOwnerCompany
                }

                // Show actions based on user role
                when (currentUser?.role) {
                    UserRole.BUSINESS_OWNER -> {
                        val canSendInterest = route.status in listOf(RouteStatus.ACTIVE, RouteStatus.PARTIALLY_BOOKED)
                        binding.btnSendInterest.visibility = if (canSendInterest) View.VISIBLE else View.GONE
                        if (canSendInterest) {
                            binding.btnSendInterest.setOnClickListener {
                                showSendInterestDialog(route.routeId)
                            }
                        }
                    }
                    UserRole.FLEET_OWNER -> {
                        if (route.fleetOwnerUid == currentUser.uid && route.bookedByUid.isNotBlank()) {
                            binding.btnCall.visibility = View.VISIBLE
                            binding.btnCall.setOnClickListener {
                                dialPhone(route.fleetOwnerPhone)
                            }
                        } else {
                            binding.btnCall.visibility = View.GONE
                        }

                        val canCancelRoute = route.fleetOwnerUid == currentUser.uid &&
                            route.status in listOf(RouteStatus.ACTIVE, RouteStatus.PARTIALLY_BOOKED)
                        binding.btnCancelRoute.visibility = if (canCancelRoute) View.VISIBLE else View.GONE
                        if (canCancelRoute) {
                            binding.btnCancelRoute.setOnClickListener {
                                showCancelRouteDialog(route.routeId)
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

    private fun showSendInterestDialog(routeId: String) {
        // New flow: let business select which cargo to attach before composing the message
        lifecycleScope.launch {
            try {
                val currentUser = repo.getUser(repo.currentUid())
                val route = repo.getTruckRoute(routeId)
                if (currentUser == null || route == null) return@launch

                // Load business cargos and filter OPEN/PENDING
                val cargos = repo.getBusinessOwnerCargos(currentUser.uid)
                    .filter { it.status.name in listOf("OPEN", "PENDING") }

                if (cargos.isEmpty()) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(R.string.no_cargo_found))
                        .setMessage(getString(R.string.no_open_cargo))
                        .setPositiveButton(getString(R.string.ok), null)
                        .show()
                    return@launch
                }

                val items = cargos.map { "${it.weightTons} ${getString(R.string.tons)} • ${it.pickupCity} → ${it.deliveryCity}" }.toTypedArray()
                var selectedIndex = 0

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.select_cargo_title))
                    .setSingleChoiceItems(items, 0) { _, which -> selectedIndex = which }
                    .setPositiveButton(getString(R.string.select)) { _, _ ->
                        val selectedCargo = cargos[selectedIndex]

                        // Show message/price dialog (reuse existing layout)
                        val dialogView = layoutInflater.inflate(R.layout.dialog_send_interest, null)
                        val etMessage = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etMessage)
                        val etOfferedPrice = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etOfferedPrice)

                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(getString(R.string.send_interest_title))
                            .setView(dialogView)
                            .setPositiveButton(getString(R.string.send_interest)) { _, _ ->
                                val message = etMessage.text.toString()
                                val price = etOfferedPrice.text.toString().toDoubleOrNull() ?: 0.0

                                val interest = BookingInterest(
                                    initiatorUid = currentUser.uid,
                                    initiatorName = currentUser.name,
                                    initiatorPhone = currentUser.phone,
                                    initiatorRole = UserRole.BUSINESS_OWNER,
                                    targetUid = route.fleetOwnerUid,
                                    targetName = route.fleetOwnerName,
                                    targetRole = UserRole.FLEET_OWNER,
                                    routeId = routeId,
                                    cargoId = selectedCargo.cargoId,
                                    message = message,
                                    offeredPrice = price,
                                    goodsWeightTons = selectedCargo.weightTons
                                )

                                // Send interest
                                lifecycleScope.launch {
                                    try {
                                        repo.sendInterest(interest)
                                        showSnackbar("${getString(R.string.interest_sent)} (Weight: ${selectedCargo.weightTons}T)")
                                    } catch (e: Exception) {
                                        showSnackbar(e.message ?: getString(R.string.error_loading))
                                    }
                                }
                            }
                            .setNegativeButton(getString(R.string.cancel), null)
                            .show()
                    }
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show()

            } catch (e: Exception) {
                showSnackbar(getString(R.string.error_loading))
            }
        }
    }

    private fun sendInterest(routeId: String, message: String, offeredPrice: Double) {
        lifecycleScope.launch {
            try {
                val currentUser = repo.getUser(repo.currentUid())
                val route = repo.getTruckRoute(routeId)
                
                if (currentUser == null || route == null) return@launch

                // Get business owner's open/pending cargos to determine weight
                val businessCargos = repo.getBusinessOwnerCargos(currentUser.uid)
                val cargoWeight = businessCargos
                    .filter { it.status.name in listOf("OPEN", "PENDING") }
                    .firstOrNull()?.weightTons ?: 0f

                val interest = BookingInterest(
                    initiatorUid = currentUser.uid,
                    initiatorName = currentUser.name,
                    initiatorPhone = currentUser.phone,
                    initiatorRole = UserRole.BUSINESS_OWNER,
                    targetUid = route.fleetOwnerUid,
                    targetName = route.fleetOwnerName,
                    targetRole = UserRole.FLEET_OWNER,
                    routeId = routeId,
                    message = message,
                    offeredPrice = offeredPrice,
                    goodsWeightTons = cargoWeight
                )

                repo.sendInterest(interest)
                showSnackbar("${getString(R.string.interest_sent)} (Weight: ${cargoWeight}T)")
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

    private fun showCancelRouteDialog(routeId: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.cancel_route_confirm_title)
            .setMessage(R.string.cancel_route_confirm_message)
            .setPositiveButton(R.string.cancel_route) { _, _ ->
                cancelRoute(routeId)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun cancelRoute(routeId: String) {
        lifecycleScope.launch {
            try {
                val route = repo.getTruckRoute(routeId)
                if (route == null) {
                    showSnackbar(getString(R.string.error_loading))
                    return@launch
                }
                val canCancel = route.status in listOf(RouteStatus.ACTIVE, RouteStatus.PARTIALLY_BOOKED)
                if (!canCancel) {
                    showSnackbar(getString(R.string.cancel_not_allowed))
                    return@launch
                }
                repo.cancelRoute(routeId)
                showSnackbar(getString(R.string.route_cancelled))
                loadRouteDetails()
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
