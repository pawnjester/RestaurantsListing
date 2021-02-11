package com.example.data.contracts.provider

interface RestaurantsProvider {
    suspend fun getRestaurants(): String
}
