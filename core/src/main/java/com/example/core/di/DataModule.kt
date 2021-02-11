package com.example.core.di

import com.example.data.impl.RestaurantsRepositoryImpl
import com.example.domain.repositories.RestaurantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun providesRestaurantsRepository(restaurantsRepositoryImpl: RestaurantsRepositoryImpl): RestaurantsRepository
}