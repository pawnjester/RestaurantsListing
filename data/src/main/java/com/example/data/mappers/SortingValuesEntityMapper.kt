package com.example.data.mappers

import com.example.data.mappers.base.EntityMapper
import com.example.data.models.SortingValuesEntity
import com.example.domain.model.SortingValues
import javax.inject.Inject

class SortingValuesEntityMapper @Inject constructor() : EntityMapper<SortingValuesEntity, SortingValues> {
    override fun mapFromEntity(entity: SortingValuesEntity): SortingValues {
        return entity.run {
            SortingValues(
                bestMatch, newest, ratingAverage, distance, popularity, averageProductPrice, deliveryCosts, minCost
            )
        }
    }

    override fun mapToEntity(domain: SortingValues): SortingValuesEntity {
        return domain.run {
            SortingValuesEntity(
                bestMatch, newest, ratingAverage, distance, popularity, averageProductPrice, deliveryCosts, minCost
            )
        }
    }
}