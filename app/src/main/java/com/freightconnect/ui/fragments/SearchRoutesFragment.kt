package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentSearchRoutesBinding
import com.freightconnect.ui.adapters.RouteAdapter
import com.freightconnect.viewmodel.SearchRoutesViewModel

class SearchRoutesFragment : Fragment() {

    private var _binding: FragmentSearchRoutesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchRoutesViewModel by viewModels()
    private lateinit var routeAdapter: RouteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchRoutesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeData()
    }

    private fun setupRecyclerView() {
        routeAdapter = RouteAdapter(
            onRouteClick = { route ->
                val action = SearchRoutesFragmentDirections.actionSearchToRouteDetail(route.routeId)
                findNavController().navigate(action)
            },
            onCallClick = { /* Not available in search */ }
        )
        
        binding.rvRoutes.apply {
            adapter = routeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            performSearch()
        }
    }

    private fun setupSearch() {
        binding.etFromCity.addTextChangedListener {
            performSearch()
        }

        binding.etToCity.addTextChangedListener {
            performSearch()
        }

        binding.btnSearch.setOnClickListener {
            performSearch()
        }

        binding.btnClearFilters.setOnClickListener {
            binding.etFromCity.text?.clear()
            binding.etToCity.text?.clear()
            performSearch()
        }
    }

    private fun performSearch() {
        val fromCity = binding.etFromCity.text.toString().trim()
        val toCity = binding.etToCity.text.toString().trim()
        viewModel.searchRoutes(fromCity, toCity)
    }

    private fun observeData() {
        viewModel.routes.observe(viewLifecycleOwner) { routes ->
            binding.swipeRefresh.isRefreshing = false
            
            if (routes.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvRoutes.visibility = View.GONE
                binding.tvEmptyMessage.text = getString(R.string.no_routes_found)
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvRoutes.visibility = View.VISIBLE
                routeAdapter.submitList(routes)
                binding.tvResultCount.text = resources.getQuantityString(
                    R.plurals.routes_found, routes.size, routes.size
                )
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
