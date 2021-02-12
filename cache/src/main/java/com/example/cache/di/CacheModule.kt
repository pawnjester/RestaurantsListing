package com.example.cache.di

import android.content.Context
import com.example.cache.room.RestaurantsDao
import com.example.cache.room.RestaurantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {


    @[Provides Singleton]
    fun providesDatabase(@ApplicationContext context: Context): RestaurantsDatabase {
        return RestaurantsDatabase.build(context)
    }

    @[Provides Singleton]
    fun providesRestaurantsDao(database: RestaurantsDatabase): RestaurantsDao {
        return database.restaurantsDao()
    }
}