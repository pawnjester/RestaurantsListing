package com.example.restaurants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.adapters.RestaurantsAdapter
import com.example.restaurants.adapters.SortingOptionsAdapter
import com.example.restaurants.databinding.FragmentHomeBinding
import com.example.restaurants.models.SortOption
import com.example.restaurants.utils.MarginItemDecoration
import com.example.restaurants.utils.getGreetingForTheDay
import com.example.restaurants.utils.observe
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

        viewModel.getRestaurants()
        val greeting = getGreetingForTheDay()
        binding.header.text = greeting

        observe(viewModel.restaurantsResult, ::observeRestaurantsList)

        restaurantsAdapter.favoriteRestaurantsCallback = {
            viewModel.favoriteRestaurants(it)
        }

        sortingOptionAdapter.sortingOptionCallback = {
            viewModel.setSortItemValue(it)
            val sortedRestaurantsList = viewModel.sortListByOption(it)
            restaurantsAdapter.setRestaurants(sortedRestaurantsList)
            sortingOptionAdapter.updateSortSelection(it)
        }

        binding.filterRestaurants.setOnClickListener {
            toggleSortLayout()
        }

        binding.searchRestaurantsEditText.doOnTextChanged { text, _, _, _ ->
            val filteredRestaurants = viewModel.filterByName(text.toString())
            restaurantsAdapter.setRestaurants(filteredRestaurants)
            binding.restaurantsCount.text = requireContext().getString(R.string.restaurant_count, filteredRestaurants.size)
        }
        sortingOptionAdapter.setOptions(SortOption.getSortItemsValue())
    }

    private fun observeRestaurantsList(restaurants: LatestUiState?) {
        restaurants?.let {
            when (it) {

                is LatestUiState.Loading -> {
                }
                is LatestUiState.Success -> {
                    restaurantsAdapter.setRestaurants(it.restaurant)
                    viewModel.setRestaurantsList(it.restaurant)
                    binding.restaurantsCount.text = requireContext().getString(R.string.restaurant_count, it.restaurant.size)
                }
                is LatestUiState.Error -> {
                }
            }
        }
    }

    private fun toggleSortLayout() {
        if (binding.sortLayout.visibility == View.VISIBLE) {
            binding.sortLayout.visibility = View.GONE
        } else {
            binding.sortLayout.visibility = View.VISIBLE
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