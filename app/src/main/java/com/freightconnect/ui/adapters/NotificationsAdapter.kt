package com.freightconnect.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freightconnect.R
import com.freightconnect.databinding.ItemNotificationCardBinding
import com.freightconnect.model.notifications
import com.freightconnect.model.NotificationType
import java.text.SimpleDateFormat
import java.util.*

/**
 * NotificationsAdapter - Display list of app notifications
 *
 * Used in:
 * - NotificationsFragment (inbox of all notifications)
 *
 * Features:
 * - Shows notification title, body, and timestamp
 * - Displays notification type as icon and color
 * - Shows read/unread status
 * - Click handling to mark as read
 */
class NotificationsAdapter(
    private val onNotificationClick: (notifications) -> Unit
) : ListAdapter<notifications, NotificationsAdapter.NotificationViewHolder>(NotificationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NotificationViewHolder(
        private val binding: ItemNotificationCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: notifications) {
            binding.apply {
                // Title and body
                tvTitle.text = notification.title
                tvBody.text = notification.body

                // Timestamp
                tvTimestamp.text = formatTimestamp(notification.createdAt)

                // Notification type icon and color
                val (typeIcon, typeColor) = getTypeIconAndColor(notification.type)
                ivTypeIcon.setImageResource(typeIcon)
                ivTypeIcon.setColorFilter(
                    ContextCompat.getColor(root.context, typeColor),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

                // Read/Unread status - bold text if unread
                if (notification.isRead) {
                    tvTitle.alpha = 0.7f
                    tvBody.alpha = 0.7f
                    ivUnreadIndicator.visibility = android.view.View.GONE
                } else {
                    tvTitle.alpha = 1.0f
                    tvBody.alpha = 1.0f
                    ivUnreadIndicator.visibility = android.view.View.VISIBLE
                }

                // Notification type badge
                tvType.text = getTypeLabel(notification.type)
                tvType.setBackgroundResource(
                    when (notification.type) {
                        NotificationType.INTEREST_RECEIVED -> R.drawable.bg_badge_interest
                        NotificationType.INTEREST_ACCEPTED -> R.drawable.bg_badge_success
                        NotificationType.INTEREST_REJECTED -> R.drawable.bg_badge_error
                        NotificationType.ROUTE_BOOKED -> R.drawable.bg_badge_info
                        NotificationType.CARGO_BOOKED -> R.drawable.bg_badge_info
                        NotificationType.IN_TRANSIT -> R.drawable.bg_badge_warning
                        NotificationType.DELIVERED -> R.drawable.bg_badge_success
                        NotificationType.CANCELLED -> R.drawable.bg_badge_error
                    }
                )

                // Click listener
                root.setOnClickListener {
                    onNotificationClick(notification)
                }
            }
        }

        private fun formatTimestamp(timestamp: Long): String {
            val now = System.currentTimeMillis()
            val diff = now - timestamp

            return when {
                diff < 60_000 -> "Just now"
                diff < 3_600_000 -> "${diff / 60_000} min ago"
                diff < 86_400_000 -> "${diff / 3_600_000} hours ago"
                diff < 604_800_000 -> "${diff / 86_400_000} days ago"
                else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(timestamp))
            }
        }

        private fun getTypeLabel(type: NotificationType): String {
            return when (type) {
                NotificationType.INTEREST_RECEIVED -> "Interest"
                NotificationType.INTEREST_ACCEPTED -> "Accepted"
                NotificationType.INTEREST_REJECTED -> "Rejected"
                NotificationType.ROUTE_BOOKED -> "Route Booked"
                NotificationType.CARGO_BOOKED -> "Cargo Booked"
                NotificationType.IN_TRANSIT -> "In Transit"
                NotificationType.DELIVERED -> "Delivered"
                NotificationType.CANCELLED -> "Cancelled"
            }
        }

        private fun getTypeIconAndColor(type: NotificationType): Pair<Int, Int> {
            return when (type) {
                NotificationType.INTEREST_RECEIVED -> Pair(android.R.drawable.ic_menu_view, R.color.color_interest)
                NotificationType.INTEREST_ACCEPTED -> Pair(android.R.drawable.ic_menu_save, R.color.color_success)
                NotificationType.INTEREST_REJECTED -> Pair(android.R.drawable.ic_menu_close_clear_cancel, R.color.color_error)
                NotificationType.ROUTE_BOOKED -> Pair(android.R.drawable.ic_menu_directions, R.color.color_info)
                NotificationType.CARGO_BOOKED -> Pair(android.R.drawable.ic_menu_agenda, R.color.color_info)
                NotificationType.IN_TRANSIT -> Pair(android.R.drawable.ic_menu_compass, R.color.color_warning)
                NotificationType.DELIVERED -> Pair(android.R.drawable.ic_input_get, R.color.color_success)
                NotificationType.CANCELLED -> Pair(android.R.drawable.ic_menu_close_clear_cancel, R.color.color_error)
            }
        }
    }
}

class NotificationDiffCallback : DiffUtil.ItemCallback<notifications>() {
    override fun areItemsTheSame(oldItem: notifications, newItem: notifications): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: notifications, newItem: notifications): Boolean {
        return oldItem == newItem
    }
}

