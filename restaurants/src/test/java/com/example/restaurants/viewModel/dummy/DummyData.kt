package com.example.restaurants.viewModel.dummy

import com.example.data.models.RestaurantsEntity
import com.example.data.models.SortingValuesEntity
import com.example.domain.model.Restaurant
import com.example.domain.model.SortingValues

object DummyData {

    fun makeSortingEntityModel(): SortingValuesEntity {
        return SortingValuesEntity(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeRestaurantEntityModel(): RestaurantsEntity {
        return RestaurantsEntity("chinese restaurant", "closed", makeSortingEntityModel(), false)
    }

    fun makeRestaurantEntityList(count: Int): List<RestaurantsEntity> {
        val list = mutableListOf<RestaurantsEntity>()
        repeat(count) {
            list.add(makeRestaurantEntityModel())
        }
        return list
    }

    fun makeSortingDomainModel(): SortingValues {
        return SortingValues(1.0, 6.0, 9.0, 15.0, 122.0, 1.0, 0.0, 3.0)
    }

    fun makeRestaurantDomainModel(): Restaurant {
        return Restaurant("chinese restaurant", "closed", makeSortingDomainModel(), false)
    }

    fun makeRestaurantDomainList(): List<Restaurant> {
        val list = mutableListOf<Restaurant>()
        list.add(Restaurant("chinese restaurant", "closed", makeSortingDomainModel(), false))
        list.add(Restaurant("flavour restaurant", "open", makeSortingDomainModel(), false))
        return list
    }
}