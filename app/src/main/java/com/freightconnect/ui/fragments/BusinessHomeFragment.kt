package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentBusinessHomeBinding
import com.freightconnect.ui.adapters.CargoAdapter
import com.freightconnect.viewmodel.BusinessHomeViewModel
import com.freightconnect.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BusinessHomeFragment : Fragment() {

    private var _binding: FragmentBusinessHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BusinessHomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var cargoAdapter: CargoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBusinessHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    private fun setupRecyclerView() {
        cargoAdapter = CargoAdapter(
            onCargoClick = { cargo ->
                val action = BusinessHomeFragmentDirections.actionHomeToCargoDetail(cargo.cargoId)
                findNavController().navigate(action)
            }
        )
        
        binding.rvCargos.apply {
            adapter = cargoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    /**
     * Setup FAB to post new cargo
     */
    private fun setupFab() {
        binding.fabPostCargo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_postCargo)
        }
    }

    /**
     * Observe dashboard data and update UI accordingly
     */
    private fun observeData() {
        viewModel.myCargos.observe(viewLifecycleOwner) { cargos ->
            binding.swipeRefresh.isRefreshing = false
            
            if (cargos.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvCargos.visibility = View.GONE
                binding.tvEmptyMessage.text = getString(R.string.no_cargo_posted)
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvCargos.visibility = View.VISIBLE
                cargoAdapter.submitList(cargos)
            }
        }

        viewModel.receivedInterests.observe(viewLifecycleOwner) { interests ->
            if (interests.isNotEmpty()) {
                binding.badgeInterests.visibility = View.VISIBLE
                binding.badgeInterests.text = interests.size.toString()
            } else {
                binding.badgeInterests.visibility = View.GONE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.shimmerLayout.visibility = View.VISIBLE
                binding.shimmerLayout.startShimmer()
                binding.rvCargos.visibility = View.GONE
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        }

        // Update dashboard metrics
        updateDashboardMetrics()
    }

    /**
     * Update dashboard metric cards
     */
    private fun updateDashboardMetrics() {
        val tvTotalCargos = binding.root.findViewById<android.widget.TextView>(R.id.tvTotalCargos)
        val tvOpenCargos = binding.root.findViewById<android.widget.TextView>(R.id.tvOpenCargos)
        val tvPendingCargos = binding.root.findViewById<android.widget.TextView>(R.id.tvPendingCargos)
        val tvBookedCargos = binding.root.findViewById<android.widget.TextView>(R.id.tvBookedCargos)

        viewModel.totalCargos.observe(viewLifecycleOwner) { count ->
            tvTotalCargos?.text = count.toString()
        }

        viewModel.openCargos.observe(viewLifecycleOwner) { count ->
            tvOpenCargos?.text = count.toString()
        }

        viewModel.pendingCargos.observe(viewLifecycleOwner) { count ->
            tvPendingCargos?.text = count.toString()
        }

        viewModel.bookedCargos.observe(viewLifecycleOwner) { count ->
            tvBookedCargos?.text = count.toString()
        }
    }

    private fun setupToolbar() {
        mainViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            val name = user?.name?.trim().orEmpty()
            val title = if (name.isNotEmpty()) {
                getString(R.string.greeting_format, name)
            } else {
                getString(R.string.greeting_fallback)
            }
            binding.toolbar.title = title
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_help) {
                showHelpDialog()
                true
            } else {
                false
            }
        }
    }

    private fun showHelpDialog() {
        val phone = getString(R.string.support_phone_value)
        val email = getString(R.string.support_email_value)
        val message = getString(R.string.support_message, phone, email)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.support_title)
            .setMessage(message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
