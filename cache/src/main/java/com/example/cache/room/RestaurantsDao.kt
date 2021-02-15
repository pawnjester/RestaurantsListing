package com.example.cache.room

import androidx.room.*
import com.example.cache.models.RestaurantsCacheModel
import com.example.cache.models.RestaurantsCacheModel.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantsDao {

    @Update
    suspend fun favoriteRestaurant(restaurants: RestaurantsCacheModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRestaurants(restaurants: List<RestaurantsCacheModel>)

    @Query("select * from $TABLE_NAME")
    fun getAllRestaurants(): Flow<List<RestaurantsCacheModel>>

}