package com.example.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.models.RestaurantsCacheModel
import com.example.cache.models.RestaurantsCacheModel.Companion.TABLE_NAME

@Dao
interface RestaurantsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteRestaurant(recipe: RestaurantsCacheModel)

    @Query("delete from $TABLE_NAME where name = :name")
    suspend fun removeRestaurant(name : String)

}