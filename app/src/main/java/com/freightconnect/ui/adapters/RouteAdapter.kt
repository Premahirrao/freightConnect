package com.freightconnect.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemRouteCardBinding
import com.freightconnect.model.RouteStatus
import com.freightconnect.model.TruckRoute
import com.freightconnect.repository.FreightRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * RouteAdapter - Display list of truck routes
 *
 * Used in:
 * - FleetHomeFragment (my posted routes)
 * - SearchRoutesFragment (browse available routes)
 *
 * Click handling: Delegates to onRouteClick callback
 */
class RouteAdapter(
    private val onRouteClick: (TruckRoute) -> Unit
) : ListAdapter<TruckRoute, RouteAdapter.RouteViewHolder>(RouteDiffCallback()) {

    private val repo = FreightRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val binding = ItemRouteCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RouteViewHolder(
        private val binding: ItemRouteCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(route: TruckRoute) {
            binding.apply {
                tvFromCity.text = route.fromCity
                tvToCity.text = route.toCity
                tvVehicleType.text = route.vehicleType.displayNameEn
                tvCapacity.text = "${route.availableCapacityTons} tons"
                tvPricePerTon.text = "₹${route.pricePerTon.toLong()}/ton"
                tvDepartureDate.text = SimpleDateFormat("dd MMM", Locale.getDefault())
                    .format(Date(route.departureDate))
                tvFleetOwner.text = route.fleetOwnerName

                // Status badge with color
                val (statusColor, statusText) = when (route.status) {
                    RouteStatus.ACTIVE -> Pair(R.color.status_active, "Active")
                    RouteStatus.BOOKED -> Pair(R.color.status_booked, "Booked")
                    RouteStatus.IN_TRANSIT -> Pair(R.color.status_in_transit, "In Transit")
                    RouteStatus.COMPLETED -> Pair(R.color.status_completed, "Completed")
                    RouteStatus.CANCELLED -> Pair(R.color.status_cancelled, "Cancelled")
                }
                tvStatus.text = statusText
                tvStatus.setBackgroundResource(statusColor)

                // Task 9: Load trust indicators from fleet profile (rating, verification, trip count)
                loadFleetOwnerTrust(route)

                // Task 9: Add availability badge
                val isAvailable = route.status == RouteStatus.ACTIVE
                val (availIcon, availText, availColor) = if (isAvailable) {
                    Triple(R.drawable.ic_check_circle, root.context.getString(R.string.available), R.color.color_success)
                } else {
                    Triple(R.drawable.ic_close, root.context.getString(R.string.already_matched), R.color.color_error)
                }
                
                ivAvailability.setImageResource(availIcon)
                ivAvailability.setColorFilter(ContextCompat.getColor(root.context, availColor), android.graphics.PorterDuff.Mode.SRC_IN)
                tvAvailability.text = availText
                tvAvailability.setTextColor(ContextCompat.getColor(root.context, availColor))

                // Navigate to route details on click
                root.setOnClickListener { onRouteClick(route) }
            }
        }

        private fun loadFleetOwnerTrust(route: TruckRoute) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val fleetProfile = repo.getFleetProfile(route.fleetOwnerUid)
                    
                    binding.apply {
                        if (fleetProfile != null) {
                            // Display rating (e.g., "★ 4.8/5")
                            if (fleetProfile.ratingsCount > 0) {
                                val rating = fleetProfile.averageRating
                                tvRating.text = "★ %.1f/5".format(rating)
                                tvRating.setTextColor(ContextCompat.getColor(root.context, R.color.color_warning))
                            }

                            // Display trip count
                            tvTripCount.text = "${fleetProfile.totalTrips} trips"

                            // Show verified badge if verified
                            if (fleetProfile.isVerified) {
                                tvVerified.text = root.context.getString(R.string.verified_badge)
                                tvVerified.visibility = View.VISIBLE
                            } else {
                                tvVerified.visibility = View.GONE
                            }
                        } else {
                            tvRating.text = ""
                            tvTripCount.text = ""
                            tvVerified.visibility = View.GONE
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private class RouteDiffCallback : DiffUtil.ItemCallback<TruckRoute>() {
        override fun areItemsTheSame(oldItem: TruckRoute, newItem: TruckRoute) =
            oldItem.routeId == newItem.routeId

        override fun areContentsTheSame(oldItem: TruckRoute, newItem: TruckRoute) =
            oldItem == newItem
    }
}
