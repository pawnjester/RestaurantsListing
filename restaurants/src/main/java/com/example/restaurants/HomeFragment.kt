package com.example.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Restaurant
import com.example.restaurants.adapters.RestaurantsAdapter
import com.example.restaurants.adapters.SortingOptionsAdapter
import com.example.restaurants.databinding.FragmentHomeBinding
import com.example.restaurants.models.SortOption
import com.example.restaurants.utils.MarginItemDecoration
import com.example.restaurants.utils.getGreetingForTheDay
import com.example.restaurants.utils.observe
import com.example.restaurants.utils.show
import com.example.restaurants.viemodel.LatestUiState
import com.example.restaurants.viemodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter

    @Inject
    lateinit var sortingOptionAdapter: SortingOptionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSortRecyclerView()
        sortingOptionAdapter.setOptions(SortOption.getSortItemsValue())
        viewModel.getRestaurants()

        val greeting = getGreetingForTheDay()
        binding.header.text = greeting


        restaurantsAdapter.favoriteRestaurantsCallback = {
            viewModel.favoriteRestaurants(it)
        }

        sortingOptionAdapter.sortingOptionCallback = {
            viewModel.sortList(it)
            sortingOptionAdapter.updateSortSelection(it)
        }

        binding.filterRestaurants.setOnClickListener {
            toggleSortLayout()
        }

        binding.searchRestaurantsEditText.doOnTextChanged { text, _, _, _ ->
            val filteredRestaurants: List<Restaurant> = viewModel.filterByName(text.toString())
            setDataView(filteredRestaurants)
        }

        observe(viewModel.restaurantsResult, ::observeRestaurantsList)
        observe(viewModel.sortOption, ::observeSortOption)
    }

    private fun observeRestaurantsList(restaurants: LatestUiState<List<Restaurant>>?) {
        restaurants?.let {
            when (it) {
                is LatestUiState.Loading -> {
                    binding.shimmerRecycler.show(true)
                    binding.shimmerRecycler.startShimmer()
                    binding.rvRestaurants.show(false)
                }
                is LatestUiState.Success -> {
                    binding.shimmerRecycler.stopShimmer()
                    binding.shimmerRecycler.show(false)
                    binding.rvRestaurants.show(true)
                    setDataView(it.restaurant)
                }
            }
        }
    }

    private fun setDataView(list: List<Restaurant>) {
        restaurantsAdapter.setRestaurants(list)
        val sizeText = if (list.isEmpty()) getString(R.string.no_restaurant) else resources.getQuantityString(R.plurals.numberOfOrders, list.size, list.size)
        binding.restaurantsCount.text = sizeText
    }

    private fun observeSortOption(option: SortOption?) {
        option?.let {
            sortingOptionAdapter.updateSortSelection(it)
        }
    }


    private fun toggleSortLayout() {
        if (binding.sortLayout.visibility == View.VISIBLE) {
            binding.sortLayout.show(false)
        } else {
            binding.sortLayout.show(true)
        }
    }


    private fun setupRecyclerView() {
        binding.rvRestaurants.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.VERTICAL, false
            )
            addItemDecoration(MarginItemDecoration(16))
            adapter = restaurantsAdapter
        }
    }

    private fun setupSortRecyclerView() {
        binding.rvSort.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
            adapter = sortingOptionAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}