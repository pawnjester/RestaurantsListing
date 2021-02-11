package com.example.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.models.RestaurantsCacheModel

@Database(
    entities = [
        RestaurantsCacheModel::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantsDatabase : RoomDatabase() {

    abstract fun restaurantsDao(): RestaurantsDao

    companion object {
        private const val DATABASE_NAME = "restaurants_db"

        fun build(context: Context): RestaurantsDatabase = Room.databaseBuilder(
            context.applicationContext,
            RestaurantsDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}