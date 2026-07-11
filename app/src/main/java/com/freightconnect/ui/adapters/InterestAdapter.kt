package com.freightconnect.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemInterestCardBinding
import com.freightconnect.model.BookingInterest
import com.freightconnect.model.InterestStatus
import com.freightconnect.repository.FreightRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class InterestAdapter(
    private val onAccept: (BookingInterest) -> Unit,
    private val onReject: (BookingInterest, String) -> Unit,  // Task 10: Added reason parameter
    private val onCall: (String) -> Unit,
    private val context: Context? = null
) : ListAdapter<BookingInterest, InterestAdapter.InterestViewHolder>(InterestDiffCallback()) {

    private val repo = FreightRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        val binding = ItemInterestCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InterestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class InterestViewHolder(
        private val binding: ItemInterestCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(interest: BookingInterest) {
            binding.apply {
                // Basic info
                tvInitiatorName.text = interest.initiatorName
                tvInitiatorRole.text = interest.initiatorRole.name.replace("_", " ")
                tvMessage.text = interest.message.ifBlank { "No message" }
                tvOfferedPrice.text = if (interest.offeredPrice > 0) 
                    "Offered: ₹${interest.offeredPrice.toLong()}" 
                else "Price: Negotiable"
                tvCreatedAt.text = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
                    .format(Date(interest.createdAt))

                // Set direction indicator (Task 2)
                setDirectionIndicator(interest)

                // Status badge
                val (statusColor, statusText) = when (interest.status) {
                    InterestStatus.PENDING -> Pair(R.color.status_pending, "Pending")
                    InterestStatus.ACCEPTED -> Pair(R.color.status_booked, "Accepted")
                    InterestStatus.REJECTED -> Pair(R.color.status_cancelled, "Rejected")
                    InterestStatus.WITHDRAWN -> Pair(R.color.status_cancelled, "Withdrawn")
                }
                tvStatus.text = statusText
                tvStatus.setBackgroundResource(statusColor)

                // Load interest context: cargo/route details (Task 3)
                loadInterestContext(interest)

                // Show action buttons only for pending interests
                if (interest.status == InterestStatus.PENDING) {
                    layoutActions.visibility = View.VISIBLE
                    btnAccept.setOnClickListener { onAccept(interest) }
                    btnReject.setOnClickListener { onReject(interest, "") }  // Task 10: Pass reason
                } else {
                    layoutActions.visibility = View.GONE
                }

                // Show call button for accepted interests
                if (interest.status == InterestStatus.ACCEPTED) {
                    btnCall.visibility = View.VISIBLE
                    btnCall.setOnClickListener { onCall(interest.initiatorPhone) }
                } else {
                    btnCall.visibility = View.GONE
                }
            }
        }

        private fun setDirectionIndicator(interest: BookingInterest) {
            val currentUid = repo.currentUid()
            val isIncoming = currentUid == interest.targetUid
            
            binding.apply {
                if (isIncoming) {
                    // Incoming: left arrow, blue color
                    ivDirection.setImageResource(R.drawable.ic_arrow_forward)
                    ivDirection.rotation = 180f
                    ivDirection.setColorFilter(ContextCompat.getColor(root.context, R.color.color_info), android.graphics.PorterDuff.Mode.SRC_IN)
                    tvDirection.text = root.context.getString(R.string.direction_incoming)
                    tvDirection.setTextColor(ContextCompat.getColor(root.context, R.color.color_info))
                } else {
                    // Outgoing: right arrow, green color
                    ivDirection.setImageResource(R.drawable.ic_arrow_forward)
                    ivDirection.rotation = 0f
                    ivDirection.setColorFilter(ContextCompat.getColor(root.context, R.color.color_success), android.graphics.PorterDuff.Mode.SRC_IN)
                    tvDirection.text = root.context.getString(R.string.direction_outgoing)
                    tvDirection.setTextColor(ContextCompat.getColor(root.context, R.color.color_success))
                }
            }
        }

        private fun loadInterestContext(interest: BookingInterest) {
            binding.apply {
                // Display goods weight prominently
                tvGoodsWeight.text = "📦 ${interest.goodsWeightTons} tons"
                
                // Load in coroutine
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        if (interest.cargoId.isNotBlank()) {
                            // Load cargo details
                            val cargo = repo.getCargoRequest(interest.cargoId)
                            if (cargo != null) {
                                tvContextLocation.text = "${cargo.pickupCity} → ${cargo.deliveryCity}"
                                tvContextWeight.text = "Route: ${cargo.requiredVehicleType.displayNameEn}"
                                tvContextDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                                    .format(Date(cargo.pickupDate))
                                cardContext.visibility = View.VISIBLE
                            } else {
                                cardContext.visibility = View.GONE
                            }
                        } else if (interest.routeId.isNotBlank()) {
                            // Load route details
                            val route = repo.getTruckRoute(interest.routeId)
                            if (route != null) {
                                tvContextLocation.text = "${route.fromCity} → ${route.toCity}"
                                tvContextWeight.text = "Truck: ${route.vehicleType.displayNameEn} (${route.remainingCapacityTons}T left)"
                                tvContextDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                                    .format(Date(route.departureDate))
                                cardContext.visibility = View.VISIBLE
                            } else {
                                cardContext.visibility = View.GONE
                            }
                        } else {
                            cardContext.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        cardContext.visibility = View.GONE
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private class InterestDiffCallback : DiffUtil.ItemCallback<BookingInterest>() {
        override fun areItemsTheSame(oldItem: BookingInterest, newItem: BookingInterest) =
            oldItem.interestId == newItem.interestId

        override fun areContentsTheSame(oldItem: BookingInterest, newItem: BookingInterest) =
            oldItem == newItem
    }
}
