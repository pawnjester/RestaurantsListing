package com.example.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cache.data.DummyData.makeRestaurantEntityList
import com.example.cache.data.DummyData.makeRestaurantEntityModel
import com.example.cache.mappers.RestaurantsCacheModelMapper
import com.example.cache.mappers.SortingValuesCacheModelMapper
import com.example.cache.room.RestaurantsDatabase
import com.example.data.contracts.cache.RestaurantCache
import com.example.data.models.RestaurantsEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantCacheImplTest {

    private lateinit var database: RestaurantsDatabase
    private lateinit var cache: RestaurantCache

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RestaurantsDatabase::class.java
        ).allowMainThreadQueries().build()

        cache = RestaurantsCacheImpl(
            database.restaurantsDao(),
            RestaurantsCacheModelMapper(SortingValuesCacheModelMapper())
        )
    }

    @Test
    fun `check that save Restaurant saves a restaurant in the database`() = runBlocking {
        val entity = makeRestaurantEntityList(2)
        cache.saveRestaurants(entity)

        val result: List<RestaurantsEntity> = cache.getAllRestaurants().first()
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `check that favorite Restaurant method`() = runBlocking {
        val entityList = makeRestaurantEntityList(2)
        val entity = makeRestaurantEntityModel()
        val editedEntity = entity.apply { isFavorite = true }

        cache.saveRestaurants(entityList)
        cache.favoriteRestaurant(entity)

        val result = cache.getAllRestaurants().first()
        assertThat(result[0]).isEqualTo(editedEntity)
    }

    @After
    fun tearDown() {
        database.close()
    }
}