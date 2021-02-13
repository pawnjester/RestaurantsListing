package com.example.cache.room

import androidx.room.*
import com.example.cache.models.RestaurantsCacheModel
import com.example.cache.models.RestaurantsCacheModel.Companion.TABLE_NAME

@Dao
interface RestaurantsDao {

    @Query("select * from $TABLE_NAME")
    suspend fun getAllUsersFavorite(): List<RestaurantsCacheModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteRestaurant(recipe: RestaurantsCacheModel)

    @Delete
    suspend fun removeRestaurant(restaurantsCacheModel: RestaurantsCacheModel)

}