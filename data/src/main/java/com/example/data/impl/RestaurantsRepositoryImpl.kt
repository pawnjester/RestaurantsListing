package com.example.data.impl

import com.example.data.contracts.cache.RestaurantCache
import com.example.data.contracts.provider.RestaurantsProvider
import com.example.data.mappers.RestaurantsEntityMapper
import com.example.data.models.RestaurantsEntity
import com.example.data.models.RestaurantsResponse
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantsProvider: RestaurantsProvider,
    private val restaurantCache: RestaurantCache,
    private val mapper: RestaurantsEntityMapper
) : RestaurantsRepository {

    override fun getRestaurants(): Flow<Result> {
        return flow {
            val response = getAllRestaurants()
            emit(Result(response))
        }
    }

    override suspend fun favoriteRestaurant(restaurant: Restaurant) {
        restaurantCache.favoriteRestaurant(mapper.mapToEntity(restaurant))
    }

    override suspend fun removeRestaurant(restaurant: Restaurant) {
        restaurantCache.removeRestaurants(mapper.mapToEntity(restaurant))
    }

    override fun getAllFavoriteRestaurants(): Flow<Result> {
        return flow {
            val response: Flow<List<RestaurantsEntity>> = restaurantCache.getAllUserFavorites()
            emitAll(response.map {
                Result(mapper.mapFromEntityList(it))
            })
        }
    }

    private fun getAllRestaurants(): List<Restaurant> {
        val restaurantsJson = restaurantsProvider.getRestaurants()
        val data = Gson().fromJson(restaurantsJson, RestaurantsResponse::class.java)
        return mapper.mapFromEntityList(data.restaurants)
    }

}