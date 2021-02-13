package com.example.restaurants.viemodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Restaurant
import com.example.domain.usecases.DeleteRestaurantUseCase
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetAllUsersFavoriteUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import com.example.restaurants.models.SortOption.Companion.AVERAGE_PRODUCT_PRICE
import com.example.restaurants.models.SortOption.Companion.BEST_MATCH
import com.example.restaurants.models.SortOption.Companion.DISTANCE
import com.example.restaurants.models.SortOption.Companion.MINIMUM_COST
import com.example.restaurants.models.SortOption.Companion.NEWEST
import com.example.restaurants.models.SortOption.Companion.POPULARITY
import com.example.restaurants.models.SortOption.Companion.RATING_AVERAGE
import com.example.restaurants.utils.toArrayList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.set

class MainViewModel @ViewModelInject constructor(
    private val getRestaurantsCase: GetRestaurantsUseCase,
    private val favoriteRestaurantCase: FavoriteRestaurantsUseCase,
    private val removeRestaurantCase: DeleteRestaurantUseCase,
    private val favoriteAllRestaurantsUseCase: GetAllUsersFavoriteUseCase
) : ViewModel() {


    private val _restaurants = MutableLiveData<LatestUiState>()
    var restaurantsResult: LiveData<LatestUiState> = _restaurants

    var restaurants = mutableListOf<Restaurant>()

    private val _restaurantsData = MutableLiveData<Pair<SortOption, List<Restaurant>>>()
    var restaurantsDataResult: LiveData<Pair<SortOption, List<Restaurant>>> = _restaurantsData


    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsCase()
                .collect {
                    restaurants.addAll(it.restaurant)
                }
        }
    }

    fun getAllUsersRestaurants() {
        viewModelScope.launch {
            favoriteAllRestaurantsUseCase()
                .collect {
                    val favorites = it.restaurant.toArrayList()
                    favorites.addAll(restaurants)
                    val response = removeDuplicates(favorites)
                    restaurants.clear()
                    restaurants.addAll(response)
                    _restaurants.value = LatestUiState.Success(response)
                    _restaurantsData.value = Pair(SortOption(BEST_MATCH), sortListByOption(response, SortOption(BEST_MATCH)))
                }
        }
    }

    private fun removeDuplicates(list: List<Restaurant>): List<Restaurant> {
        val distinctRestaurants = LinkedHashMap<String, Restaurant>()
        for (restaurant in list) {
            if (distinctRestaurants[restaurant.name] == null)
                distinctRestaurants[restaurant.name] = restaurant
        }
        return ArrayList(distinctRestaurants.values)
    }


    fun favoriteRestaurants(restaurant: Restaurant) {
        viewModelScope.launch {
            if (restaurant.isFavorite) {
                val removeRecipe = restaurant.apply { isFavorite = false }
                removeRestaurantCase(removeRecipe)
                _restaurants.value = LatestUiState.Success(restaurants)
            } else {
                val favoriteRecipe = restaurant.apply { isFavorite = true }
                favoriteRestaurantCase(favoriteRecipe)
                _restaurants.value = LatestUiState.Success(restaurants)
            }
        }
    }

    fun filterByName(name: String): List<Restaurant> {
        return restaurants.filter { restaurant ->
            restaurant.name.toLowerCase(Locale.getDefault()).contains(name.toLowerCase(Locale.getDefault()))
        }
    }

    fun sortListByOption(restaurants: List<Restaurant>, option: SortOption): List<Restaurant> {
        return when (option.option) {
            AVERAGE_PRODUCT_PRICE -> restaurants.sortedByDescending { it.sortingValues.averageProductPrice }
            BEST_MATCH -> restaurants.sortedByDescending { it.sortingValues.bestMatch }
            NEWEST -> restaurants.sortedByDescending { it.sortingValues.newest }
            POPULARITY -> restaurants.sortedByDescending { it.sortingValues.popularity }
            MINIMUM_COST -> restaurants.sortedByDescending { it.sortingValues.minCost }
            DISTANCE -> restaurants.sortedByDescending { it.sortingValues.distance }
            RATING_AVERAGE -> restaurants.sortedByDescending { it.sortingValues.ratingAverage }
            else -> restaurants
        }
    }
}

sealed class LatestUiState {
    data class Success(val restaurant: List<Restaurant>) : LatestUiState()
    object Loading : LatestUiState()
    data class Error(val exception: String) : LatestUiState()
}