package com.example.restaurantslisting.repositories

import com.example.data.impl.RestaurantsRepositoryImpl
import com.example.data.mappers.RestaurantsEntityMapper
import com.example.data.mappers.SortingValuesEntityMapper
import com.example.domain.model.Result
import com.example.restaurantslisting.data.DummyData.makeRestaurantDomainModel
import com.example.restaurantslisting.fakes.FakeRestaurantCache
import com.example.restaurantslisting.fakes.FakeRestaurantProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RestaurantRepositoryImplTest {

    private val provider = FakeRestaurantProvider()
    private val restaurantCache = FakeRestaurantCache()
    private val mapper = RestaurantsEntityMapper(SortingValuesEntityMapper())

    private val repository = RestaurantsRepositoryImpl(provider, restaurantCache, mapper)

    @ExperimentalCoroutinesApi
    @Test
    fun `get Restaurant`() = runBlockingTest {
        val result: Result = repository.getRestaurants().first()
        assertThat(result.restaurant).isNotEmpty()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `favorite Restaurant`() = runBlockingTest {
        val restaurant = makeRestaurantDomainModel()
        repository.favoriteRestaurant(restaurant)
        val result: Result = repository.getRestaurants().first()
        assertThat(restaurant.isFavorite).isEqualTo(result.restaurant.first().isFavorite)
    }


}