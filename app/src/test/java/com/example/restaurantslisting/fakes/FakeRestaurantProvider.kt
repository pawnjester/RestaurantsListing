package com.example.restaurantslisting.fakes

import com.example.data.contracts.provider.RestaurantsProvider
import com.example.data.models.RestaurantsEntity

class FakeRestaurantProvider : RestaurantsProvider {
    private val provider = LinkedHashMap<String, String>()
    override fun getRestaurants(): String {
        return "data"
    }
}