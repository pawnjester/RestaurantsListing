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
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantsProvider: RestaurantsProvider,
    private val restaurantCache: RestaurantCache,
    private val mapper: RestaurantsEntityMapper
) : RestaurantsRepository {

    override fun getRestaurants(): Flow<Result> {
        return getAllRestaurants().map {
            Result(it)
        }
    }

    override suspend fun favoriteRestaurant(restaurant: Restaurant) {
        restaurantCache.favoriteRestaurant(mapper.mapToEntity(restaurant))
    }


    override fun getAllFavoriteRestaurants(): Flow<Result> {
        return flow {
            val response: Flow<List<RestaurantsEntity>> = restaurantCache.getAllUserFavorites()
            emitAll(response.map {
                Result(mapper.mapFromEntityList(it))
            })
        }
    }

    private fun getAllRestaurants(): Flow<List<Restaurant>> {
        val map: Flow<List<Restaurant>> = restaurantCache.getAllRestaurants().map {
            if (it.isEmpty()) {
                val restaurantsJson = restaurantsProvider.getRestaurants()
                val data = Gson().fromJson(restaurantsJson, RestaurantsResponse::class.java)
                restaurantCache.saveRestaurants(data.restaurants)
                mapper.mapFromEntityList(data.restaurants)
            } else {
                mapper.mapFromEntityList(it)
            }

        }
        return map

    }

}