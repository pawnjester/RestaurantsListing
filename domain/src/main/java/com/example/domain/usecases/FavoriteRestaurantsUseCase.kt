package com.example.domain.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.model.Restaurant
import com.example.domain.repositories.RestaurantsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val postExecution: PostExecutorThread
) {

    suspend operator fun invoke(restaurant: Restaurant) {
        withContext(postExecution.io) {
            repository.favoriteRestaurant(restaurant)
        }
    }

}