package com.freightconnect.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freightconnect.R
import com.freightconnect.databinding.FragmentSearchCargosBinding
import com.freightconnect.ui.adapters.CargoAdapter
import com.freightconnect.viewmodel.SearchCargosViewModel

class SearchCargosFragment : Fragment() {

    private var _binding: FragmentSearchCargosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchCargosViewModel by viewModels()
    private lateinit var cargoAdapter: CargoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchCargosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeData()
    }

    private fun setupRecyclerView() {
        cargoAdapter = CargoAdapter(
            onCargoClick = { cargo ->
                val action = SearchCargosFragmentDirections.actionSearchToCargoDetail(cargo.cargoId)
                findNavController().navigate(action)
            }
        )
        
        binding.rvCargos.apply {
            adapter = cargoAdapter
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
        viewModel.searchCargos(fromCity, toCity)
    }

    private fun observeData() {
        viewModel.cargos.observe(viewLifecycleOwner) { cargos ->
            binding.swipeRefresh.isRefreshing = false
            
            if (cargos.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvCargos.visibility = View.GONE
                binding.tvEmptyMessage.text = getString(R.string.no_cargo_found)
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvCargos.visibility = View.VISIBLE
                cargoAdapter.submitList(cargos)
                binding.tvResultCount.text = resources.getQuantityString(
                    R.plurals.cargos_found, cargos.size, cargos.size
                )
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
