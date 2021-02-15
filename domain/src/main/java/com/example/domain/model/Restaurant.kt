package com.example.domain.model

import com.example.domain.model.SortOptionsObject.BEST_MATCH

data class Restaurant(
    val name: String,
    val status: String,
    val sortingValues: SortingValues,
    var isFavorite: Boolean = false,
    var sortString: String = BEST_MATCH

) {
    companion object {
        const val CLOSED = "closed"
        const val OPEN = "open"
        const val ORDER_AHEAD = "order ahead"
    }

    fun displaySortValue()  : String {
        val sortValue = when (sortString) {
            SortOptionsObject.AVERAGE_PRODUCT_PRICE -> sortingValues.averageProductPrice
            BEST_MATCH -> sortingValues.bestMatch
            SortOptionsObject.NEWEST -> sortingValues.newest
            SortOptionsObject.POPULARITY -> sortingValues.popularity
            SortOptionsObject.MINIMUM_COST -> sortingValues.minCost
            SortOptionsObject.DISTANCE -> sortingValues.distance
            SortOptionsObject.RATING_AVERAGE -> sortingValues.ratingAverage
            else -> sortingValues.deliveryCosts
        }
        return sortValue.toString()
    }

    fun getRestaurantStatus() : Int {
        return when (status) {
            OPEN -> 3
            ORDER_AHEAD -> 2
            else -> 1
        }
    }
}