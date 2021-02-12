package com.example.data.contracts.cache

import com.example.data.models.RestaurantsEntity

interface RestaurantCache {

    suspend fun favoriteRestaurant(restaurant: RestaurantsEntity)

    suspend fun removeRestaurants(name: String)
}