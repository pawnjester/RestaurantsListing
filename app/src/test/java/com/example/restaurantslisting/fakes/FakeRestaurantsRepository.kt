package com.example.restaurantslisting.fakes

import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import com.example.restaurantslisting.data.DummyData.makeRestaurantDomainList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRestaurantsRepository : RestaurantsRepository {
    private val cache = LinkedHashMap<String, Restaurant>()

    override fun getRestaurants(): Flow<Result> {
        return flowOf(Result(makeRestaurantDomainList(2)))
    }

    override suspend fun favoriteRestaurant(restaurant: Restaurant) {
        cache[restaurant.name] = restaurant
    }
}