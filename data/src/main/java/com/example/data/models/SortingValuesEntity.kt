package com.example.data.models

data class SortingValuesEntity(
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Double,
    val popularity: Double,
    val averageProductPrice: Double,
    val deliveryCosts: Double,
    val minCost: Double
)