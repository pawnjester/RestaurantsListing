package com.example.cache.data

import com.example.cache.models.RestaurantsCacheModel
import com.example.cache.models.SortingValuesCacheModel
import com.example.data.models.RestaurantsEntity
import com.example.data.models.SortingValuesEntity

object DummyData {

    fun makeSortingValuesCacheModel(): SortingValuesCacheModel {
        return SortingValuesCacheModel(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeRestaurantsCacheModel(): RestaurantsCacheModel {
        return RestaurantsCacheModel("chinese restaurants", "open", makeSortingValuesCacheModel(), false)
    }
    fun makeSortingEntityModel(): SortingValuesEntity {
        return SortingValuesEntity(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeRestaurantEntityModel(): RestaurantsEntity {
        return RestaurantsEntity("chinese restaurant", "closed", makeSortingEntityModel(), false)
    }

    fun makeRestaurantEntityList(count: Int) : List<RestaurantsEntity> {
        val list = mutableListOf<RestaurantsEntity>()
        repeat(count) {
            list.add(makeRestaurantEntityModel())
        }
        return list
    }
}