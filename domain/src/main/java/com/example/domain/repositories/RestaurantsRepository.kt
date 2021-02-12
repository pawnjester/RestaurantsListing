package com.example.domain.repositories

import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {

    fun getRestaurants() : Flow<Result>

    suspend fun favoriteRestaurant(restaurant: Restaurant)

    suspend fun removeRestaurant(name: String)

    fun sortRestaurants(option: String) : Flow<Result>

    fun filterRestaurants(name: String) : Flow<Result>
}