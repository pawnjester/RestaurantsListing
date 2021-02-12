package com.example.cache.impl

import com.example.cache.mappers.RestaurantsCacheModelMapper
import com.example.cache.room.RestaurantsDao
import com.example.data.contracts.cache.RestaurantCache
import com.example.data.models.RestaurantsEntity
import javax.inject.Inject

class RestaurantsCacheImpl @Inject constructor(
    private val dao: RestaurantsDao,
    private val mapper: RestaurantsCacheModelMapper
) : RestaurantCache {

    override suspend fun favoriteRestaurant(restaurant: RestaurantsEntity) {
        dao.favoriteRestaurant(mapper.mapToModel(restaurant))
    }

    override suspend fun removeRestaurants(name: String) {
        dao.removeRestaurant(name)
    }
}