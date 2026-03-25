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
                    tvCapacity.text = "${route.availableCapacityTons} ${getString(R.string.tons)}"
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
                        binding.btnSendInterest.visibility = View.VISIBLE
                        binding.btnSendInterest.setOnClickListener {
                            showSendInterestDialog(route.routeId)
                        }
                    }
                    UserRole.FLEET_OWNER -> {
                        if (route.fleetOwnerUid == currentUser.uid && route.bookedByUid.isNotBlank()) {
                            binding.btnCall.visibility = View.VISIBLE
                            binding.btnCall.setOnClickListener {
                                dialPhone(route.fleetOwnerPhone)
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
        val dialogView = layoutInflater.inflate(R.layout.dialog_send_interest, null)
        val etMessage = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etMessage)
        val etOfferedPrice = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etOfferedPrice)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.send_interest_title)
            .setView(dialogView)
            .setPositiveButton(R.string.send_interest) { _, _ ->
                val message = etMessage.text.toString()
                val price = etOfferedPrice.text.toString().toDoubleOrNull() ?: 0.0
                sendInterest(routeId, message, price)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun sendInterest(routeId: String, message: String, offeredPrice: Double) {
        lifecycleScope.launch {
            try {
                val currentUser = repo.getUser(repo.currentUid())
                val route = repo.getTruckRoute(routeId)
                
                if (currentUser == null || route == null) return@launch

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
                    offeredPrice = offeredPrice
                )

                repo.sendInterest(interest)
                showSnackbar(getString(R.string.interest_sent))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
