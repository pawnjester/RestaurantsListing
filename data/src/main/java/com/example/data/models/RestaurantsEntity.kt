package com.example.data.models

data class RestaurantsEntity(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesEntity,
    var isFavorite: Boolean = false
)