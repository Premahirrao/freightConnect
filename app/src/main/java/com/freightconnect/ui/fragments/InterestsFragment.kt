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
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding get() = _binding!!
    private val repo = FreightRepository()
    private lateinit var interestAdapter: InterestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInterestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTabs()
        loadReceivedInterests()
    }

    private fun setupRecyclerView() {
        interestAdapter = InterestAdapter(
            onAccept = { interest ->
                acceptInterest(interest.interestId)
            },
            onReject = { interest ->
                rejectInterest(interest.interestId)
            },
            onCall = { phone ->
                val intent = android.content.Intent(android.content.Intent.ACTION_DIAL)
                intent.data = android.net.Uri.parse("tel:$phone")
                startActivity(intent)
            }
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
        }
    }

    private fun setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> loadReceivedInterests()
                    1 -> loadSentInterests()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
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
            } catch (e: Exception) {
                showSnackbar(e.message ?: getString(R.string.error_loading))
            }
        }
    }

    private fun rejectInterest(interestId: String) {
        lifecycleScope.launch {
            try {
                repo.rejectInterest(interestId)
                showSnackbar(getString(R.string.interest_rejected))
                loadReceivedInterests()
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
        _binding = null
    }
}
