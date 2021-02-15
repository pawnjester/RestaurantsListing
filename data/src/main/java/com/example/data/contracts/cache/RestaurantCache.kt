package com.example.data.contracts.cache

import com.example.data.models.RestaurantsEntity
import kotlinx.coroutines.flow.Flow

interface RestaurantCache {

    suspend fun favoriteRestaurant(restaurant: RestaurantsEntity)

    fun getAllRestaurants(): Flow<List<RestaurantsEntity>>

    suspend fun saveRestaurants(list: List<RestaurantsEntity>)

}