package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentFleetHomeBinding
import com.freightconnect.ui.adapters.RouteAdapter
import com.freightconnect.viewmodel.FleetHomeViewModel
import com.google.android.material.snackbar.Snackbar

class FleetHomeFragment : Fragment() {

    private var _binding: FragmentFleetHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FleetHomeViewModel by viewModels()
    private lateinit var routeAdapter: RouteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFleetHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeData()
    }

    private fun setupRecyclerView() {
        routeAdapter = RouteAdapter(
            onRouteClick = { route ->
                val action = FleetHomeFragmentDirections.actionHomeToRouteDetail(route.routeId)
                findNavController().navigate(action)
            },
            onCallClick = { phone ->
                // Will be implemented in detail screen after booking
            }
        )
        
        binding.rvRoutes.apply {
            adapter = routeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadMyRoutes()
        }
    }

    private fun setupFab() {
        binding.fabPostRoute.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_postRoute)
        }
    }

    private fun observeData() {
        viewModel.myRoutes.observe(viewLifecycleOwner) { routes ->
            binding.swipeRefresh.isRefreshing = false
            
            if (routes.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvRoutes.visibility = View.GONE
                binding.tvEmptyMessage.text = getString(R.string.no_routes_posted)
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvRoutes.visibility = View.VISIBLE
                routeAdapter.submitList(routes)
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
                binding.rvRoutes.visibility = View.GONE
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
