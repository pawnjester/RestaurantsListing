package com.example.cache.di

import com.example.cache.impl.RestaurantsCacheImpl
import com.example.data.contracts.cache.RestaurantCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantsCacheModule {

    @Binds
    abstract fun providesRestaurantsCache(recipeCacheImpl: RestaurantsCacheImpl): RestaurantCache
}