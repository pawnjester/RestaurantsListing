package com.example.restaurants.viemodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Restaurant
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import com.example.domain.model.SortOptionsObject.AVERAGE_PRODUCT_PRICE
import com.example.domain.model.SortOptionsObject.BEST_MATCH
import com.example.domain.model.SortOptionsObject.DELIVERY_COST
import com.example.domain.model.SortOptionsObject.DISTANCE
import com.example.domain.model.SortOptionsObject.MINIMUM_COST
import com.example.domain.model.SortOptionsObject.NEWEST
import com.example.domain.model.SortOptionsObject.POPULARITY
import com.example.domain.model.SortOptionsObject.RATING_AVERAGE
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val getRestaurantsCase: GetRestaurantsUseCase,
    private val favoriteRestaurantCase: FavoriteRestaurantsUseCase,
) : ViewModel() {


    private val _restaurants = MutableLiveData<LatestUiState>()
    var restaurantsResult: LiveData<LatestUiState> = _restaurants

    var restaurants = mutableListOf<Restaurant>()

    private val _sortOption = MutableLiveData<SortOption>()
    var sortOption: LiveData<SortOption> = _sortOption

    var sortBy: String = ""


    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsCase()
                .collect {
                    restaurants.clear()
                    restaurants.addAll(it.restaurant)
                    if (sortBy.isEmpty()) {
                        if (restaurants.isNotEmpty()) {
                            sortBy = BEST_MATCH
                            _sortOption.value = SortOption(sortBy)
                            val sortedRestaurants = restaurants.sortedByDescending { it.sortingValues.bestMatch }
                            sortRestaurantsByDescending(sortedRestaurants)
                        }
                    } else {
                        _restaurants.value = LatestUiState.Success(restaurants)
                    }
                }
        }
    }

    fun favoriteRestaurants(restaurant: Restaurant) {
        viewModelScope.launch {
            val favoriteRestaurant = restaurant.apply { isFavorite = !restaurant.isFavorite }
            favoriteRestaurantCase(favoriteRestaurant)
        }
    }

    fun filterByName(name: String): List<Restaurant> {
        return restaurants.filter { restaurant ->
            restaurant.name.toLowerCase(Locale.getDefault()).contains(name.toLowerCase(Locale.getDefault()))
        }
    }


    fun sortListByOption(option: SortOption) {
        sortBy = option.option
        val sortedRestaurants = when (sortBy) {
            AVERAGE_PRODUCT_PRICE -> restaurants.sortedByDescending { it.sortingValues.averageProductPrice }
            BEST_MATCH -> restaurants.sortedByDescending { it.sortingValues.bestMatch }
            NEWEST -> restaurants.sortedByDescending { it.sortingValues.newest }
            POPULARITY -> restaurants.sortedByDescending { it.sortingValues.popularity }
            MINIMUM_COST -> restaurants.sortedByDescending { it.sortingValues.minCost }
            DISTANCE -> restaurants.sortedByDescending { it.sortingValues.distance }
            RATING_AVERAGE -> restaurants.sortedByDescending { it.sortingValues.ratingAverage }
            DELIVERY_COST -> restaurants.sortedByDescending { it.sortingValues.deliveryCosts }
            else -> restaurants
        }
        sortRestaurantsByDescending(sortedRestaurants)
    }

    private fun sortRestaurantsByDescending(sortedRestaurants: List<Restaurant>) {
        restaurants.clear()
        restaurants.addAll(sortedRestaurants)
        val sortedList = restaurants.sortedByDescending { it.isFavorite }
        restaurants.clear()
        restaurants.addAll(sortedList)
        restaurants.forEach {
            it.sortString = sortBy
        }
        _restaurants.value = LatestUiState.Success(restaurants)
    }




}

sealed class LatestUiState {
    data class Success(val restaurant: List<Restaurant>) : LatestUiState()
    object Loading : LatestUiState()
    data class Error(val exception: String) : LatestUiState()
}