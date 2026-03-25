package com.freightconnect.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemRouteCardBinding
import com.freightconnect.model.RouteStatus
import com.freightconnect.model.TruckRoute
import java.text.SimpleDateFormat
import java.util.*

class RouteAdapter(
    private val onRouteClick: (TruckRoute) -> Unit,
    private val onCallClick: (String) -> Unit
) : ListAdapter<TruckRoute, RouteAdapter.RouteViewHolder>(RouteDiffCallback()) {

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

                // Status badge
                val (statusColor, statusText) = when (route.status) {
                    RouteStatus.ACTIVE -> Pair(R.color.status_active, "Active")
                    RouteStatus.BOOKED -> Pair(R.color.status_booked, "Booked")
                    RouteStatus.IN_TRANSIT -> Pair(R.color.status_in_transit, "In Transit")
                    RouteStatus.COMPLETED -> Pair(R.color.status_completed, "Completed")
                    RouteStatus.CANCELLED -> Pair(R.color.status_cancelled, "Cancelled")
                }
                tvStatus.text = statusText
                tvStatus.setBackgroundResource(statusColor)

                root.setOnClickListener { onRouteClick(route) }
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
