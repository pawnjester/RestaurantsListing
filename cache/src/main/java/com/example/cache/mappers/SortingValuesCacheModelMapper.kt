package com.example.cache.mappers

import com.example.cache.mappers.base.CacheModelMapper
import com.example.cache.models.SortingValuesCacheModel
import com.example.data.models.SortingValuesEntity
import javax.inject.Inject

class SortingValuesCacheModelMapper @Inject constructor() : CacheModelMapper<SortingValuesCacheModel, SortingValuesEntity> {
    override fun mapToModel(entity: SortingValuesEntity): SortingValuesCacheModel {
        return entity.run {
            SortingValuesCacheModel(
                bestMatch, newest, ratingAverage, distance, popularity, averageProductPrice, deliveryCosts, minCost
            )
        }
    }

    override fun mapToEntity(model: SortingValuesCacheModel): SortingValuesEntity {
        return model.run {
            SortingValuesEntity(
                bestMatch, newest, ratingAverage, distance, popularity, averageProductPrice, deliveryCosts, minCost
            )
        }
    }
}