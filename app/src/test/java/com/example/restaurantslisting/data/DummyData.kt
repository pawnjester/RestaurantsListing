package com.example.restaurantslisting.data

import com.example.data.models.RestaurantsEntity
import com.example.data.models.SortingValuesEntity
import com.example.domain.model.Restaurant
import com.example.domain.model.SortingValues

object DummyData {

    fun makeSortingEntityModel(): SortingValuesEntity {
        return SortingValuesEntity(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeSortingDomainModel(): SortingValues {
        return SortingValues(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeRestaurantEntityModel(): RestaurantsEntity {
        return RestaurantsEntity("chinese restaurant", "closed", makeSortingEntityModel(), false)
    }

    fun makeRestaurantDomainModel(): Restaurant {
        return Restaurant("chinese restaurant", "closed", makeSortingDomainModel(), false)
    }

    fun makeRestaurantEntityList(count: Int) : List<RestaurantsEntity> {
        val list = mutableListOf<RestaurantsEntity>()
        repeat(count) {
            list.add(makeRestaurantEntityModel())
        }
        return list
    }

    fun makeRestaurantDomainList(count: Int) : List<Restaurant> {
        val list = mutableListOf<Restaurant>()
        repeat(count) {
            list.add(DummyData.makeRestaurantDomainModel())
        }
        return list
    }


}