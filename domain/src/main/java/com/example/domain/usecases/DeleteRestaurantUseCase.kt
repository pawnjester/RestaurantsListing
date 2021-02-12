package com.example.domain.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.repositories.RestaurantsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteRestaurantUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val postExecution: PostExecutorThread
) {

    suspend operator fun invoke(name: String) {
        withContext(postExecution.io) {
            repository.removeRestaurant(name)
        }
    }
}