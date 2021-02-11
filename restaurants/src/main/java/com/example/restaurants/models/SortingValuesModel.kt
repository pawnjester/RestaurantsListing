package com.example.restaurants.models

data class SortingValuesModel(
    val bestMatch: Int,
    val newest: Int,
    val ratingAverage: Int,
    val distance: Int,
    val popularity: Int,
    val averageProductPrice: Int,
    val deliveryCosts: Int,
    val minCost: Int
)