package com.example.cache.mappers

import com.example.cache.data.DummyData.makeSortingEntityModel
import com.example.cache.data.DummyData.makeSortingValuesCacheModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SortingValuesCacheModelMapperTest {

    private val sut = SortingValuesCacheModelMapper()

    @Test
    fun `maps entity to Model`() {
        val expected = makeSortingEntityModel()

        val actual = sut.mapToModel(expected)
        assertThat(actual.averageProductPrice).isEqualTo(expected.averageProductPrice)
        assertThat(actual.bestMatch).isEqualTo(expected.bestMatch)
        assertThat(actual.deliveryCosts).isEqualTo(expected.deliveryCosts)
        assertThat(actual.minCost).isEqualTo(expected.minCost)
        assertThat(actual.newest).isEqualTo(expected.newest)
        assertThat(actual.ratingAverage).isEqualTo(expected.ratingAverage)
        assertThat(actual.popularity).isEqualTo(expected.popularity)
    }

    @Test
    fun `maps model to entity`() {
        val expected = makeSortingValuesCacheModel()

        val actual = sut.mapToEntity(expected)
        assertThat(actual.averageProductPrice).isEqualTo(expected.averageProductPrice)
        assertThat(actual.bestMatch).isEqualTo(expected.bestMatch)
        assertThat(actual.deliveryCosts).isEqualTo(expected.deliveryCosts)
        assertThat(actual.minCost).isEqualTo(expected.minCost)
        assertThat(actual.newest).isEqualTo(expected.newest)
        assertThat(actual.ratingAverage).isEqualTo(expected.ratingAverage)
        assertThat(actual.popularity).isEqualTo(expected.popularity)
    }
}