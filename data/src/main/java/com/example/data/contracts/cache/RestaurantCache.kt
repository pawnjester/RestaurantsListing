package com.example.data.contracts.cache

import com.example.data.models.RestaurantsEntity
import kotlinx.coroutines.flow.Flow

interface RestaurantCache {

    suspend fun favoriteRestaurant(restaurant: RestaurantsEntity)

    suspend fun removeRestaurants(restaurant: RestaurantsEntity)

    fun getAllUserFavorites(): Flow<List<RestaurantsEntity>>
}