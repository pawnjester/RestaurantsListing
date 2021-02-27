package com.example.restaurants.viemodel

import androidx.lifecycle.*
import com.example.domain.model.Restaurant
import com.example.domain.model.SortOptionsObject.AVERAGE_PRODUCT_PRICE
import com.example.domain.model.SortOptionsObject.BEST_MATCH
import com.example.domain.model.SortOptionsObject.DELIVERY_COST
import com.example.domain.model.SortOptionsObject.DISTANCE
import com.example.domain.model.SortOptionsObject.MINIMUM_COST
import com.example.domain.model.SortOptionsObject.NEWEST
import com.example.domain.model.SortOptionsObject.POPULARITY
import com.example.domain.model.SortOptionsObject.RATING_AVERAGE
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRestaurantsCase: GetRestaurantsUseCase,
    private val favoriteRestaurantCase: FavoriteRestaurantsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _restaurants = MutableLiveData<LatestUiState<List<Restaurant>>>()
    var restaurantsResult: LiveData<LatestUiState<List<Restaurant>>> = _restaurants

    private var restaurants = mutableListOf<Restaurant>()

    private val _sortOption = MutableLiveData<SortOption>()
    var sortOption: LiveData<SortOption> = _sortOption

    private var sortBy: String = ""

    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsCase().onStart {
                _restaurants.value = LatestUiState.Loading
            }
                .collect { result ->
                    restaurants.clear()
                    restaurants.addAll(result.restaurant)
                    if (sortBy.isEmpty()) {
                        sortBy = BEST_MATCH
                        _sortOption.value = SortOption(sortBy)
                        val sortedList = restaurants.sortedByDescending { it.sortingValues.bestMatch }
                        sortByDescending(sortedList)
                    } else {
                        sortList(SortOption(sortBy))
                    }
                }
        }
    }

    fun sortList(option: SortOption) {
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
        sortByDescending(sortedRestaurants)
    }

    private fun sortByDescending(sortedRestaurants: List<Restaurant>) {
        val favorites = mutableListOf<Restaurant>()
        val notFavorites = mutableListOf<Restaurant>()
        val list = sortedRestaurants.sortedByDescending { it.getRestaurantStatus() }
        restaurants.clear()
        restaurants.addAll(list)
        restaurants.map {
            if (it.isFavorite) favorites.add(it) else notFavorites.add(it)
        }
        val sortedFavorites = favorites.sortedByDescending { it.getRestaurantStatus() }
        val notSortedFavorites = notFavorites.sortedByDescending { it.getRestaurantStatus() }
        val totalList = listOf(sortedFavorites, notSortedFavorites)
        val orderedList = totalList.flatten()
        restaurants.clear()
        restaurants.addAll(orderedList)
        restaurants.forEach {
            it.sortString = sortBy
        }
        _restaurants.value = LatestUiState.Success(restaurants)
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
}

sealed class LatestUiState<out T : Any> {
    data class Success<out T : Any>(val restaurant: T) : LatestUiState<T>()
    object Loading : LatestUiState<Nothing>()
}