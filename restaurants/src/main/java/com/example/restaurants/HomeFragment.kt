package com.example.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Restaurant
import com.example.restaurants.adapters.RestaurantsAdapter
import com.example.restaurants.databinding.FragmentHomeBinding
import com.example.restaurants.utils.MarginItemDecoration
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

        viewModel.getRestaurants()

        observe(viewModel.restaurantsResult, ::observeRestaurantsList)
    }

    private fun observeRestaurantsList(restaurants: LatestUiState<List<Restaurant>>?) {
        restaurants?.let {
            when (it) {

                is LatestUiState.Loading -> {
                }
                is LatestUiState.Success -> {
                    restaurantsAdapter.setRestaurants(it.restaurant)
                    binding.restaurantsCount.text = requireContext().getString(R.string.restaurant_count, it.restaurant.size)
                }
                is LatestUiState.Error -> {
                }
            }
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}