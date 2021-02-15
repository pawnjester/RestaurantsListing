package com.example.restaurantslisting.usecases

import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import com.example.domain.usecases.GetRestaurantsUseCase
import com.example.restaurantslisting.fakes.TestPostExecutionThread
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetRestaurantsUseCaseTest {

    private lateinit var sut: GetRestaurantsUseCase

    @Mock
    lateinit var repository: RestaurantsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = GetRestaurantsUseCase(repository, TestPostExecutionThread())
    }

    @Test
    fun `ensures getRestaurants is called`() {
        val result = Result(
            listOf(
                Restaurant(
                    randomBuild(), randomBuild(),
                    randomBuild(),
                    randomBuild()
                )
            )
        )
        whenever(
            repository.getRestaurants()
        ).thenReturn(
            flow {
                emit(
                    result
                )
            }
        )

        sut.execute()
        verify(repository).getRestaurants()
    }
}