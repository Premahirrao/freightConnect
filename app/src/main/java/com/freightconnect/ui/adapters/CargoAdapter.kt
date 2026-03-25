package com.freightconnect.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemCargoCardBinding
import com.freightconnect.model.CargoRequest
import com.freightconnect.model.CargoStatus
import java.text.SimpleDateFormat
import java.util.*

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

                // Status badge
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
