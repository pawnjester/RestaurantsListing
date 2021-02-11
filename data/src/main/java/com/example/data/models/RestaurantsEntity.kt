package com.example.data.models

import com.example.domain.model.SortingValues

data class RestaurantsEntity(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesEntity
)