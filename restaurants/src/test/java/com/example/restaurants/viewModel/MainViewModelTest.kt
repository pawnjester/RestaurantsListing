package com.example.restaurants.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.model.Restaurant
import com.example.domain.usecases.FavoriteRestaurantsUseCase
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurants.models.SortOption
import com.example.restaurants.viemodel.LatestUiState
import com.example.restaurants.viemodel.MainViewModel
import com.example.restaurants.viewModel.dummy.DummyData.makeRestaurantDomainList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
    fun `test filter by name `() {
        val result = sut.filterByName("flavour restaurant")
        assertThat(result).isEqualTo(listOf<Restaurant>())
    }

    @Test
    fun `first flow for getRestaurants`() {
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
    fun `test when sortby is empty`() {

    }

}