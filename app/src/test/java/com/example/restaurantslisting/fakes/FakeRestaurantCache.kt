package com.example.restaurantslisting.fakes

import com.example.data.contracts.cache.RestaurantCache
import com.example.data.models.RestaurantsEntity
import com.example.restaurantslisting.data.DummyData.makeRestaurantEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRestaurantCache : RestaurantCache {
    private val cache = LinkedHashMap<String, RestaurantsEntity>()

    override suspend fun favoriteRestaurant(restaurant: RestaurantsEntity) {
        cache[restaurant.name] = restaurant
    }

    override fun getAllRestaurants(): Flow<List<RestaurantsEntity>> {
        return flowOf(makeRestaurantEntityList(2))
    }

    override suspend fun saveRestaurants(list: List<RestaurantsEntity>) {
        cache[list.first().name] = list[0]
    }
}