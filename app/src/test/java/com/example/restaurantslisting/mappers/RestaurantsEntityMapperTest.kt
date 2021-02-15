package com.example.restaurantslisting.mappers

import com.example.data.mappers.RestaurantsEntityMapper
import com.example.data.mappers.SortingValuesEntityMapper
import com.example.data.models.RestaurantsEntity
import com.example.restaurantslisting.data.DummyData.makeRestaurantDomainModel
import com.example.restaurantslisting.data.DummyData.makeRestaurantEntityModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RestaurantsEntityMapperTest {


    private val sut = RestaurantsEntityMapper(SortingValuesEntityMapper())

    @Test
    fun `maps domain to entity`() {
        val expected = makeRestaurantDomainModel()

        val actual: RestaurantsEntity = sut.mapToEntity(expected)
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.status).isEqualTo(expected.status)
        assertThat(actual.sortingValues.bestMatch).isEqualTo(expected.sortingValues.bestMatch)
        assertThat(actual.sortingValues.averageProductPrice).isEqualTo(expected.sortingValues.averageProductPrice)
        assertThat(actual.sortingValues.deliveryCosts).isEqualTo(expected.sortingValues.deliveryCosts)
        assertThat(actual.sortingValues.distance).isEqualTo(expected.sortingValues.distance)
        assertThat(actual.sortingValues.newest).isEqualTo(expected.sortingValues.newest)
        assertThat(actual.sortingValues.ratingAverage).isEqualTo(expected.sortingValues.ratingAverage)
        assertThat(actual.sortingValues.minCost).isEqualTo(expected.sortingValues.minCost)
        assertThat(actual.isFavorite).isEqualTo(expected.isFavorite)
    }

    @Test
    fun `maps entity to domain`() {

        val expected = makeRestaurantEntityModel()
        val actual = sut.mapFromEntity(expected)
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.status).isEqualTo(expected.status)
        assertThat(actual.isFavorite).isEqualTo(expected.isFavorite)
        assertThat(actual.sortingValues.bestMatch).isEqualTo(expected.sortingValues.bestMatch)
        assertThat(actual.sortingValues.averageProductPrice).isEqualTo(expected.sortingValues.averageProductPrice)
        assertThat(actual.sortingValues.deliveryCosts).isEqualTo(expected.sortingValues.deliveryCosts)
        assertThat(actual.sortingValues.distance).isEqualTo(expected.sortingValues.distance)
        assertThat(actual.sortingValues.newest).isEqualTo(expected.sortingValues.newest)
        assertThat(actual.sortingValues.ratingAverage).isEqualTo(expected.sortingValues.ratingAverage)
        assertThat(actual.sortingValues.minCost).isEqualTo(expected.sortingValues.minCost)

    }
}