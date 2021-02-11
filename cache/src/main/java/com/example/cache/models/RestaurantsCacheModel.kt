package com.example.cache.models

data class RestaurantsCacheModel(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesCacheModel
)