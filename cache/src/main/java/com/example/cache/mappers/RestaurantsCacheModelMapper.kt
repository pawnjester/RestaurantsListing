package com.example.cache.mappers

import com.example.cache.mappers.base.CacheModelMapper
import com.example.cache.models.RestaurantsCacheModel
import com.example.data.models.RestaurantsEntity
import javax.inject.Inject

class RestaurantsCacheModelMapper @Inject constructor(
    private val mapper: SortingValuesCacheModelMapper
) :
    CacheModelMapper<RestaurantsCacheModel, RestaurantsEntity> {
    override fun mapToModel(entity: RestaurantsEntity): RestaurantsCacheModel {
        return entity.run {
            RestaurantsCacheModel(
                name = name, status = status, sortingValues = mapper.mapToModel(sortingValues), isFavorite
            )
        }
    }

    override fun mapToEntity(model: RestaurantsCacheModel): RestaurantsEntity {
        return model.run {
            RestaurantsEntity(
                name, status, mapper.mapToEntity(sortingValues), isFavorite
            )
        }
    }
}