package com.example.data.impl

import com.example.data.contracts.cache.RestaurantCache
import com.example.data.contracts.provider.RestaurantsProvider
import com.example.data.mappers.RestaurantsEntityMapper
import com.example.data.models.RestaurantsResponse
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantsProvider: RestaurantsProvider,
    private val restaurantCache: RestaurantCache,
    private val mapper: RestaurantsEntityMapper
) : RestaurantsRepository {

    override fun getRestaurants(): Flow<Result> {
        return flow {
            val restaurantsJson = restaurantsProvider.getRestaurants()
            val data = Gson().fromJson(restaurantsJson, RestaurantsResponse::class.java)
            emit(Result(mapper.mapFromEntityList(data.restaurants)))
        }
    }

    override suspend fun favoriteRestaurant(restaurant: Restaurant) {
        restaurantCache.favoriteRestaurant(mapper.mapToEntity(restaurant))
    }

    override suspend fun removeRestaurant(name: String) {
        restaurantCache.removeRestaurants(name)
    }

    override fun sortRestaurants(option: String): Flow<Result> {
        return flow {

        }
    }

    override fun filterRestaurants(name: String): Flow<Result> {
        return flow {

        }
    }
}