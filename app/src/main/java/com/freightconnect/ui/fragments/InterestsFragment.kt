package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentInterestsBinding
import com.freightconnect.repository.FreightRepository
import com.freightconnect.ui.adapters.InterestAdapter
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding get() = _binding!!
    private val repo = FreightRepository()
    private lateinit var interestAdapter: InterestAdapter
    private var unreadBadge: BadgeDrawable? = null
    
    // Task 11: Real-time listener registrations
    private var receivedInterestsListener: ListenerRegistration? = null
    private var sentInterestsListener: ListenerRegistration? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInterestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTabs()
        setupBadgeNotifications()
        // Task 11: Start real-time listener for received interests (default tab)
        setupReceivedInterestsListener()
    }

    private fun setupRecyclerView() {
        interestAdapter = InterestAdapter(
            onAccept = { interest ->
                acceptInterest(interest.interestId)
            },
            onReject = { interest, _ ->
                // Task 10: Show rejection reason dialog
                com.freightconnect.ui.dialogs.RejectionReasonDialog.show(
                    childFragmentManager
                ) { reason ->
                    rejectInterest(interest.interestId, reason)
                }
            },
            onCall = { phone ->
                val intent = android.content.Intent(android.content.Intent.ACTION_DIAL)
                intent.data = android.net.Uri.parse("tel:$phone")
                startActivity(intent)
            },
            context = requireContext()
        )

        binding.rvInterests.apply {
            adapter = interestAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            if (binding.tabLayout.selectedTabPosition == 0) {
                loadReceivedInterests()
            } else {
                loadSentInterests()
            }
            setupBadgeNotifications()
        }
    }

    private fun setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        // Task 11: Switch to received interests listener
                        setupReceivedInterestsListener()
                    }
                    1 -> {
                        // Task 11: Switch to sent interests listener
                        setupSentInterestsListener()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    /**
     * Task 11: Setup real-time listener for received interests
     */
    private fun setupReceivedInterestsListener() {
        binding.progressBar.visibility = View.VISIBLE
        
        // Remove old listener
        sentInterestsListener?.remove()
        receivedInterestsListener?.remove()
        
        receivedInterestsListener = repo.listenToReceivedInterests(
            repo.currentUid(),
            onUpdate = { interests ->
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                
                if (interests.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvInterests.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvInterests.visibility = View.VISIBLE
                    interestAdapter.submitList(interests)
                }
                setupBadgeNotifications()
            },
            onError = { e ->
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        )
    }

    /**
     * Task 11: Setup real-time listener for sent interests
     */
    private fun setupSentInterestsListener() {
        binding.progressBar.visibility = View.VISIBLE
        
        // Remove old listener
        receivedInterestsListener?.remove()
        sentInterestsListener?.remove()
        
        sentInterestsListener = repo.listenToSentInterests(
            repo.currentUid(),
            onUpdate = { interests ->
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                
                if (interests.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvInterests.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvInterests.visibility = View.VISIBLE
                    interestAdapter.submitList(interests)
                }
            },
            onError = { e ->
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        )
    }

    /**
     * Task 4: Setup badge notifications showing unread interest count
     */
    private fun setupBadgeNotifications() {
        lifecycleScope.launch {
            try {
                val unreadCount = repo.getReceivedInterests(repo.currentUid())
                    .count { it.status.name == "PENDING" }

                if (unreadCount > 0) {
                    val badge = BadgeDrawable.create(requireContext())
                    badge.number = unreadCount
                    badge.backgroundColor = requireContext().getColor(R.color.color_error)
                    unreadBadge = badge
                } else {
                    unreadBadge = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadReceivedInterests() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                val interests = repo.getReceivedInterests(repo.currentUid())
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false

                if (interests.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvInterests.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvInterests.visibility = View.VISIBLE
                    interestAdapter.submitList(interests)
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun loadSentInterests() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                val interests = repo.getSentInterests(repo.currentUid())
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false

                if (interests.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvInterests.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvInterests.visibility = View.VISIBLE
                    interestAdapter.submitList(interests)
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun acceptInterest(interestId: String) {
        lifecycleScope.launch {
            try {
                repo.acceptInterest(interestId)
                showSnackbar(getString(R.string.interest_accepted))
                loadReceivedInterests()
                setupBadgeNotifications()
            } catch (e: Exception) {
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun rejectInterest(interestId: String, reason: String = "") {
        lifecycleScope.launch {
            try {
                repo.rejectInterest(interestId, reason)
                showSnackbar(getString(R.string.rejection_reason_saved))
                loadReceivedInterests()
                setupBadgeNotifications()
            } catch (e: Exception) {
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Task 11: Remove real-time listeners to avoid memory leaks
        receivedInterestsListener?.remove()
        sentInterestsListener?.remove()
        _binding = null
    }
}
