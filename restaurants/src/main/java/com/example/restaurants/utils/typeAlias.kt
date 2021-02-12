package com.example.restaurants.utils

import com.example.domain.model.Restaurant
import com.example.restaurants.models.SortOption

typealias FavoriteRestaurantsCallback = (Restaurant) -> Unit
typealias SortOptionCallback = (SortOption) -> Unit