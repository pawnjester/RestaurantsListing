package com.example.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.models.RestaurantsCacheModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RestaurantsCacheModel(
    @PrimaryKey
    val name: String,
    val status: String,
    val sortingValues: SortingValuesCacheModel,
    var isFavorite: Boolean = false,
) {
    companion object {
        const val TABLE_NAME = "restaurants"
    }
}