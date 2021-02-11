package com.example.restaurants.viemodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Restaurant
import com.example.domain.usecases.GetRestaurantsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getRestaurantsCase: GetRestaurantsUseCase
) : ViewModel() {


    private val _restaurants = MutableLiveData<LatestUiState<List<Restaurant>>>()
    var restaurantsResult: LiveData<LatestUiState<List<Restaurant>>> = _restaurants


    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsCase().collect {
                _restaurants.value = LatestUiState.Success(it.restaurant)
            }
        }
    }
}

sealed class LatestUiState<out T : Any> {
    data class Success<out T : Any>(val restaurant: T) : LatestUiState<T>()
    object Loading : LatestUiState<Nothing>()
    object Empty : LatestUiState<Nothing>()
    data class Error(val exception: String) : LatestUiState<Nothing>()
}