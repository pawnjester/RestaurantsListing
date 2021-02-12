package com.example.restaurants.viemodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Restaurant
import com.example.domain.usecases.DeleteRestaurantUseCase
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import com.example.restaurants.models.SortOption.Companion.AVERAGE_PRODUCT_PRICE
import com.example.restaurants.models.SortOption.Companion.BEST_MATCH
import com.example.restaurants.models.SortOption.Companion.DISTANCE
import com.example.restaurants.models.SortOption.Companion.MINIMUM_COST
import com.example.restaurants.models.SortOption.Companion.NEWEST
import com.example.restaurants.models.SortOption.Companion.POPULARITY
import com.example.restaurants.models.SortOption.Companion.RATING_AVERAGE
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val getRestaurantsCase: GetRestaurantsUseCase,
    private val favoriteRestaurantCase: FavoriteRestaurantsUseCase,
    private val removeRestaurantCase: DeleteRestaurantUseCase
) : ViewModel() {


    private val _restaurants = MutableLiveData<LatestUiState>()
    var restaurantsResult: LiveData<LatestUiState> = _restaurants

    private var sortItem: SortOption? = null
    private var restaurants = mutableListOf<Restaurant>()

    fun setSortItemValue(item: SortOption) {
        sortItem = item

    }

    fun setRestaurantsList(list: List<Restaurant>) {
        restaurants.addAll(list)
    }


    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsCase().collect {
                _restaurants.value = LatestUiState.Success(it.restaurant)
            }
        }
    }

    fun favoriteRestaurants(restaurants: Restaurant) {
        viewModelScope.launch {
            if (restaurants.isFavorite) {
                val removeRecipe = restaurants.copy(isFavorite = false)
                removeRestaurantCase(removeRecipe.name)
            } else {
                val favoritedRecipe = restaurants.copy(isFavorite = true)
                favoriteRestaurantCase(favoritedRecipe)
            }
        }
    }

    fun filterByName(name: String): List<Restaurant> {
        return restaurants.filter { restaurant ->
            restaurant.name.toLowerCase(Locale.getDefault()).contains(name.toLowerCase(Locale.getDefault()))
        }
    }

    fun sortListByOption(option: SortOption): List<Restaurant> {
        return when (option.option) {
            AVERAGE_PRODUCT_PRICE -> restaurants.sortedWith(compareBy { it.sortingValues.averageProductPrice })
            BEST_MATCH -> restaurants.sortedWith(compareBy { it.sortingValues.bestMatch })
            NEWEST -> restaurants.sortedWith(compareBy { it.sortingValues.newest })
            POPULARITY -> restaurants.sortedWith(compareBy { it.sortingValues.popularity })
            MINIMUM_COST -> restaurants.sortedWith(compareBy { it.sortingValues.minCost })
            DISTANCE -> restaurants.sortedWith(compareBy { it.sortingValues.distance })
            RATING_AVERAGE -> restaurants.sortedWith(compareBy { it.sortingValues.ratingAverage })
            else -> restaurants
        }
    }
}

//sealed class LatestUiState<out T : Any> {
//    data class Success<out T : Any>(val restaurant: T) : LatestUiState<T>()
//    object Loading : LatestUiState<Nothing>()
//    object Empty : LatestUiState<Nothing>()
//    data class Error(val exception: String) : LatestUiState<Nothing>()
//}

sealed class LatestUiState {
    data class Success(val restaurant: List<Restaurant>) : LatestUiState()
    object Loading : LatestUiState()
    object Empty : LatestUiState()
    data class Error(val exception: String) : LatestUiState()
}