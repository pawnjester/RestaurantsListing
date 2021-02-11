package com.example.provider.di

import com.example.data.contracts.provider.RestaurantsProvider
import com.example.provider.impl.RestaurantsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantsProviderModule {

    @Binds
    abstract fun providesRestaurants(impl: RestaurantsProviderImpl): RestaurantsProvider
}