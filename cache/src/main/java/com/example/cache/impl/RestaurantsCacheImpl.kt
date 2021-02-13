package com.example.cache.impl

import com.example.cache.mappers.RestaurantsCacheModelMapper
import com.example.cache.room.RestaurantsDao
import com.example.data.contracts.cache.RestaurantCache
import com.example.data.models.RestaurantsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantsCacheImpl @Inject constructor(
    private val dao: RestaurantsDao,
    private val mapper: RestaurantsCacheModelMapper
) : RestaurantCache {

    override suspend fun favoriteRestaurant(restaurant: RestaurantsEntity) {
        dao.favoriteRestaurant(mapper.mapToModel(restaurant))
    }

    override suspend fun removeRestaurants(restaurant: RestaurantsEntity) {
        dao.removeRestaurant(mapper.mapToModel(restaurant))
    }

    override fun getAllUserFavorites(): Flow<List<RestaurantsEntity>> {
        return flow {
            val models = dao.getAllUsersFavorite()
            emit(
                models.map {
                    mapper.mapToEntity(it)
                }
            )
        }
    }


}