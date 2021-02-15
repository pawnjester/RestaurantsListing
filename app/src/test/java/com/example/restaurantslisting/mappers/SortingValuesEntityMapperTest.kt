package com.example.restaurantslisting.mappers

import com.example.data.mappers.SortingValuesEntityMapper
import com.example.restaurantslisting.data.DummyData.makeSortingDomainModel
import com.example.restaurantslisting.data.DummyData.makeSortingEntityModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SortingValuesEntityMapperTest {

    private val sut = SortingValuesEntityMapper()

    @Test
    fun `maps domain to entity`() {
        val expected = makeSortingDomainModel()

        val actual = sut.mapToEntity(expected)
        assertThat(actual.minCost).isEqualTo(expected.minCost)
        assertThat(actual.averageProductPrice).isEqualTo(expected.averageProductPrice)
        assertThat(actual.deliveryCosts).isEqualTo(expected.deliveryCosts)
        assertThat(actual.distance).isEqualTo(expected.distance)
        assertThat(actual.newest).isEqualTo(expected.newest)
        assertThat(actual.ratingAverage).isEqualTo(expected.ratingAverage)
        assertThat(actual.bestMatch).isEqualTo(expected.bestMatch)
    }

    @Test
    fun `maps entity to domain`() {
        val expected = makeSortingDomainModel()

        val actual = sut.mapFromEntity(makeSortingEntityModel())
        assertThat(actual.minCost).isEqualTo(expected.minCost)
        assertThat(actual.averageProductPrice).isEqualTo(expected.averageProductPrice)
        assertThat(actual.deliveryCosts).isEqualTo(expected.deliveryCosts)
        assertThat(actual.distance).isEqualTo(expected.distance)
        assertThat(actual.newest).isEqualTo(expected.newest)
        assertThat(actual.ratingAverage).isEqualTo(expected.ratingAverage)
        assertThat(actual.bestMatch).isEqualTo(expected.bestMatch)
    }
}