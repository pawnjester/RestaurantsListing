package com.example.domain.repositories

import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {

    fun getRestaurants() : Flow<Result>

    suspend fun favoriteRestaurant(restaurant: Restaurant)

    fun getAllFavoriteRestaurants() : Flow<Result>

}