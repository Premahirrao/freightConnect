package com.freightconnect.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemCargoCardBinding
import com.freightconnect.model.CargoRequest
import com.freightconnect.model.CargoStatus
import java.text.SimpleDateFormat
import java.util.*

/**
 * CargoAdapter - Display list of cargo requests
 *
 * Used in:
 * - BusinessHomeFragment (my posted cargo)
 * - SearchCargosFragment (browse available cargo for fleet owners)
 *
 * Click handling: Delegates to onCargoClick callback for navigation to detail screen
 */
class CargoAdapter(
    private val onCargoClick: (CargoRequest) -> Unit
) : ListAdapter<CargoRequest, CargoAdapter.CargoViewHolder>(CargoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CargoViewHolder {
        val binding = ItemCargoCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CargoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CargoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CargoViewHolder(
        private val binding: ItemCargoCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cargo: CargoRequest) {
            binding.apply {
                tvGoodsType.text = cargo.goodsType.displayNameEn
                tvPickupCity.text = cargo.pickupCity
                tvDeliveryCity.text = cargo.deliveryCity
                tvWeight.text = "${cargo.weightTons} tons"
                tvBudget.text = "₹${cargo.budgetAmount.toLong()}"
                tvPickupDate.text = SimpleDateFormat("dd MMM", Locale.getDefault())
                    .format(Date(cargo.pickupDate))
                tvBusinessOwner.text = cargo.businessOwnerName

                // Status badge with color-coded display
                val (statusColor, statusText) = when (cargo.status) {
                    CargoStatus.OPEN -> Pair(R.color.status_open, "Open")
                    CargoStatus.PENDING -> Pair(R.color.status_pending, "Pending")
                    CargoStatus.BOOKED -> Pair(R.color.status_booked, "Booked")
                    CargoStatus.IN_TRANSIT -> Pair(R.color.status_in_transit, "In Transit")
                    CargoStatus.DELIVERED -> Pair(R.color.status_completed, "Delivered")
                    CargoStatus.CANCELLED -> Pair(R.color.status_cancelled, "Cancelled")
                }
                tvStatus.text = statusText
                tvStatus.setBackgroundResource(statusColor)

                // Task 8: Add availability badge (green checkmark for available, red lock for matched)
                val isAvailable = cargo.status == CargoStatus.OPEN || cargo.status == CargoStatus.PENDING
                val (availIcon, availText, availColor) = if (isAvailable) {
                    Triple(R.drawable.ic_check_circle, root.context.getString(R.string.available), R.color.color_success)
                } else {
                    Triple(R.drawable.ic_close, root.context.getString(R.string.already_matched), R.color.color_error)
                }
                
                ivAvailability.setImageResource(availIcon)
                ivAvailability.setColorFilter(ContextCompat.getColor(root.context, availColor), android.graphics.PorterDuff.Mode.SRC_IN)
                tvAvailability.text = availText
                tvAvailability.setTextColor(ContextCompat.getColor(root.context, availColor))

                // Navigate to cargo details on click
                root.setOnClickListener { onCargoClick(cargo) }
            }
        }
    }

    private class CargoDiffCallback : DiffUtil.ItemCallback<CargoRequest>() {
        override fun areItemsTheSame(oldItem: CargoRequest, newItem: CargoRequest) =
            oldItem.cargoId == newItem.cargoId

        override fun areContentsTheSame(oldItem: CargoRequest, newItem: CargoRequest) =
            oldItem == newItem
    }
}
