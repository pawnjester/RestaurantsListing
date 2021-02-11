package com.example.cache.models

data class SortingValuesCacheModel(
    val bestMatch: Int,
    val newest: Int,
    val ratingAverage: Int,
    val distance: Int,
    val popularity: Int,
    val averageProductPrice: Int,
    val deliveryCosts: Int,
    val minCost: Int
)