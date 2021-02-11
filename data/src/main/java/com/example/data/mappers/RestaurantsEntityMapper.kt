package com.example.data.mappers

import com.example.data.mappers.base.EntityMapper
import com.example.data.models.RestaurantsEntity
import com.example.domain.model.Restaurant
import javax.inject.Inject

class RestaurantsEntityMapper @Inject constructor(
    private val mapper: SortingValuesEntityMapper
) : EntityMapper<RestaurantsEntity, Restaurant> {
    override fun mapFromEntity(entity: RestaurantsEntity): Restaurant {
        return entity.run {
            Restaurant(
                name, status, mapper.mapFromEntity(sortingValues)
            )
        }
    }

    override fun mapToEntity(domain: Restaurant): RestaurantsEntity {
        return domain.run {
            RestaurantsEntity(
                name, status, mapper.mapToEntity(sortingValues)
            )
        }
    }
}