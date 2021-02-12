package com.example.domain.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import com.example.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val postExecution: PostExecutorThread
) : FlowUseCase<String, Result>() {
    override val dispatcher: CoroutineDispatcher
        get() = postExecution.io

    override fun execute(params: String?): Flow<Result> {
        requireNotNull(params)
        return repository.filterRestaurants(params)
    }
}