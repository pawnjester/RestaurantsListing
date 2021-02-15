package com.example.restaurantslisting.usecases

import com.example.domain.model.Restaurant
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.restaurantslisting.data.DummyData.makeRestaurantDomainModel
import com.example.restaurantslisting.fakes.FakeRestaurantsRepository
import com.example.restaurantslisting.fakes.TestPostExecutionThread
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class FavoriteRestaurantUseCaseTest {

    private val restaurantRepository = FakeRestaurantsRepository()
    private val favoriteRestaurant = FavoriteRestaurantsUseCase(restaurantRepository, TestPostExecutionThread())

    @ExperimentalCoroutinesApi
    @Test
    fun `check that favorite Restaurant`() = runBlockingTest {
        val restaurant: Restaurant = makeRestaurantDomainModel()
        favoriteRestaurant(restaurant)
        val result: Restaurant = restaurantRepository.getRestaurants().first().restaurant.first()
        assertThat(restaurant).isEqualTo(result)
    }
}