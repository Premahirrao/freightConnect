package com.freightconnect.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemInterestCardBinding
import com.freightconnect.model.BookingInterest
import com.freightconnect.model.InterestStatus
import java.text.SimpleDateFormat
import java.util.*

class InterestAdapter(
    private val onAccept: (BookingInterest) -> Unit,
    private val onReject: (BookingInterest) -> Unit,
    private val onCall: (String) -> Unit
) : ListAdapter<BookingInterest, InterestAdapter.InterestViewHolder>(InterestDiffCallback()) {

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
                tvInitiatorName.text = interest.initiatorName
                tvInitiatorRole.text = interest.initiatorRole.name.replace("_", " ")
                tvMessage.text = interest.message.ifBlank { "No message" }
                tvOfferedPrice.text = if (interest.offeredPrice > 0) 
                    "Offered: ₹${interest.offeredPrice.toLong()}" 
                else "Price: Negotiable"
                tvCreatedAt.text = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
                    .format(Date(interest.createdAt))

                // Status badge
                val (statusColor, statusText) = when (interest.status) {
                    InterestStatus.PENDING -> Pair(R.color.status_pending, "Pending")
                    InterestStatus.ACCEPTED -> Pair(R.color.status_booked, "Accepted")
                    InterestStatus.REJECTED -> Pair(R.color.status_cancelled, "Rejected")
                    InterestStatus.WITHDRAWN -> Pair(R.color.status_cancelled, "Withdrawn")
                }
                tvStatus.text = statusText
                tvStatus.setBackgroundResource(statusColor)

                // Show action buttons only for pending interests
                if (interest.status == InterestStatus.PENDING) {
                    layoutActions.visibility = View.VISIBLE
                    btnAccept.setOnClickListener { onAccept(interest) }
                    btnReject.setOnClickListener { onReject(interest) }
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
    }

    private class InterestDiffCallback : DiffUtil.ItemCallback<BookingInterest>() {
        override fun areItemsTheSame(oldItem: BookingInterest, newItem: BookingInterest) =
            oldItem.interestId == newItem.interestId

        override fun areContentsTheSame(oldItem: BookingInterest, newItem: BookingInterest) =
            oldItem == newItem
    }
}
