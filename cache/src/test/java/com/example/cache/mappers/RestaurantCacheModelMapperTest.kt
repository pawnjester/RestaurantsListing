package com.example.cache.mappers

import com.example.cache.data.DummyData.makeRestaurantEntityModel
import com.example.cache.data.DummyData.makeRestaurantsCacheModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RestaurantCacheModelMapperTest {

    private val sut = RestaurantsCacheModelMapper(SortingValuesCacheModelMapper())

    @Test
    fun `maps model to entity`() {
        val expected = makeRestaurantsCacheModel()

        val actual = sut.mapToEntity(expected)
        assertThat(actual.status).isEqualTo(expected.status)
        assertThat(actual.isFavorite).isEqualTo(expected.isFavorite)
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.sortingValues.bestMatch).isEqualTo(expected.sortingValues.bestMatch)
        assertThat(actual.sortingValues.ratingAverage).isEqualTo(expected.sortingValues.ratingAverage)
        assertThat(actual.sortingValues.newest).isEqualTo(expected.sortingValues.newest)
        assertThat(actual.sortingValues.distance).isEqualTo(expected.sortingValues.distance)
        assertThat(actual.sortingValues.averageProductPrice).isEqualTo(expected.sortingValues.averageProductPrice)
        assertThat(actual.sortingValues.minCost).isEqualTo(expected.sortingValues.minCost)
        assertThat(actual.sortingValues.popularity).isEqualTo(expected.sortingValues.popularity)
    }

    @Test
    fun `maps entity to model`() {
        val expected = makeRestaurantEntityModel()

        val actual = sut.mapToModel(expected)
        assertThat(actual.status).isEqualTo(expected.status)
        assertThat(actual.isFavorite).isEqualTo(expected.isFavorite)
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.sortingValues.bestMatch).isEqualTo(expected.sortingValues.bestMatch)
        assertThat(actual.sortingValues.ratingAverage).isEqualTo(expected.sortingValues.ratingAverage)
        assertThat(actual.sortingValues.newest).isEqualTo(expected.sortingValues.newest)
        assertThat(actual.sortingValues.distance).isEqualTo(expected.sortingValues.distance)
        assertThat(actual.sortingValues.averageProductPrice).isEqualTo(expected.sortingValues.averageProductPrice)
        assertThat(actual.sortingValues.minCost).isEqualTo(expected.sortingValues.minCost)
        assertThat(actual.sortingValues.popularity).isEqualTo(expected.sortingValues.popularity)
    }
}