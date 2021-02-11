package com.example.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestaurantsCacheModel(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val name: String,
    val status: String,
    val sortingValues: SortingValuesCacheModel
)