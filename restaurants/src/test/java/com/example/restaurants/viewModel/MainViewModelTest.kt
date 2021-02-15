package com.example.restaurants.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.model.Restaurant
import com.example.domain.repositories.RestaurantsRepository
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import com.example.restaurants.viemodel.LatestUiState
import com.example.restaurants.viemodel.MainViewModel
import com.example.restaurants.viewModel.dummy.DummyData
import com.example.restaurants.viewModel.dummy.DummyData.makeOrderedRestaurantDomainList
import com.example.restaurants.viewModel.dummy.DummyData.makeRestaurantDomainList
import com.example.restaurants.viewModel.dummy.DummyData.makeRestaurantDomainModel
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var sut: MainViewModel

    @Mock
    lateinit var getRestaurantsCase: GetRestaurantsUseCase

    @Mock
    lateinit var favoriteRestaurantCase: FavoriteRestaurantsUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var sortObserver: Observer<SortOption>

    @Mock
    private lateinit var uiObserver: Observer<LatestUiState<List<Restaurant>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<SortOption>

    @Captor
    private lateinit var captorUI: ArgumentCaptor<LatestUiState<List<Restaurant>>>

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        sut = MainViewModel(
            getRestaurantsCase,
            favoriteRestaurantCase
        )
    }

    @Test
    fun `test filter by name returns a list of restaurants `() {
        val result = sut.filterByName("flavour restaurant")
        assertThat(result).isEqualTo(listOf<Restaurant>())
    }

    @Test
    fun `check that first flow for getRestaurants`() {
        sut.sortOption.observeForever(sortObserver)
        val result = com.example.domain.model.Result(makeRestaurantDomainList())
        Mockito.`when`(getRestaurantsCase()).thenReturn(
            flow {
                emit(result)
            }
        )
        sut.getRestaurants()
        Mockito.verify(sortObserver).onChanged(captor.capture())
        assertThat(captor.value).isEqualTo(SortOption("Best Match"))
    }

    @Test
    fun `test to confirm that it sorts a list by open, order ahead and closed`() {
        sut.restaurantsResult.observeForever(uiObserver)
        val result = com.example.domain.model.Result(makeRestaurantDomainList())
        Mockito.`when`(getRestaurantsCase()).thenReturn(
            flow {
                emit(result)
            }
        )
        sut.getRestaurants()
        Mockito.verify(uiObserver, times(2)).onChanged(captorUI.capture())
        assertThat(captorUI.value).isEqualTo(LatestUiState.Success(makeOrderedRestaurantDomainList()))
    }

    @Test
    fun `test to confirm favorite restaurant`() = runBlocking {
        sut.restaurantsResult.observeForever(uiObserver)
        val restaurant = makeRestaurantDomainModel()
        Mockito.`when`(favoriteRestaurantCase(restaurant)).thenReturn(
            Unit
        )
        sut.favoriteRestaurants(restaurant)
        assertThat(restaurant.isFavorite).isEqualTo(true)
        assertThat(restaurant.name).isEqualTo("chinese restaurant")
        assertThat(restaurant.status).isEqualTo("closed")

    }

}