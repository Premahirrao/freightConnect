package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentNotificationsBinding
import com.freightconnect.repository.FreightRepository
import com.freightconnect.ui.adapters.NotificationsAdapter
import kotlinx.coroutines.launch

/**
 * NotificationsFragment - Display user's notification inbox
 *
 * Features:
 * - Load and display notifications from Firestore
 * - Mark notifications as read when clicked
 * - Pull-to-refresh to reload notifications
 * - Empty state when no notifications
 * - Error handling with user feedback
 */
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val repo = FreightRepository()
    private lateinit var notificationsAdapter: NotificationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupPullToRefresh()
        loadNotifications()
    }

    /**
     * Setup RecyclerView with adapter and layout manager
     */
    private fun setupRecyclerView() {
        notificationsAdapter = NotificationsAdapter(
            onNotificationClick = { notification ->
                // Mark notification as read
                if (!notification.isRead) {
                    markNotificationAsRead(notification.id)
                }
                // Could navigate to detail or related content based on notification type
                // Example: if (notification.relatedRouteId.isNotEmpty()) navigateToRoute()
            }
        )

        binding.rvNotifications.apply {
            adapter = notificationsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    /**
     * Setup pull-to-refresh functionality
     */
    private fun setupPullToRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            loadNotifications()
        }
    }

    /**
     * Load notifications from Firestore
     */
    private fun loadNotifications() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                val uid = repo.currentUid()

                if (uid.isEmpty()) {
                    showError("User not authenticated")
                    return@launch
                }

                val notifications = repo.getUserNotifications(uid)
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false

                if (notifications.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvNotifications.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvNotifications.visibility = View.VISIBLE
                    notificationsAdapter.submitList(notifications)
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                showError("Failed to load notifications: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    /**
     * Mark a single notification as read
     */
    private fun markNotificationAsRead(notificationId: String) {
        lifecycleScope.launch {
            try {
                repo.markNotificationAsRead(notificationId)
                // Refresh to show updated read status
                loadNotifications()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Show error message to user
     */
    private fun showError(message: String) {
        binding.tvError.apply {
            text = message
            visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
