package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentBusinessHomeBinding
import com.freightconnect.ui.adapters.CargoAdapter
import com.freightconnect.viewmodel.BusinessHomeViewModel

class BusinessHomeFragment : Fragment() {

    private var _binding: FragmentBusinessHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BusinessHomeViewModel by viewModels()
    private lateinit var cargoAdapter: CargoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBusinessHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeData()
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
            viewModel.loadMyCargos()
        }
    }

    private fun setupFab() {
        binding.fabPostCargo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_postCargo)
        }
    }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
