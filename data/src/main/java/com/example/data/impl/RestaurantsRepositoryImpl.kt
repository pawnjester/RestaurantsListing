package com.example.data.impl

import com.example.domain.model.Result
import com.example.domain.repositories.RestaurantsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor() : RestaurantsRepository {

    override fun getRestaurants(): Flow<Result> {
        return flow {

        }
    }
}