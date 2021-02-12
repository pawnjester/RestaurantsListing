package com.example.domain.model

data class Restaurant(
        val name: String,
        val status: String,
        val sortingValues: SortingValues,
        var isFavorite: Boolean = false

) {
        companion object {
                const val CLOSED = "closed"
                const val OPEN = "open"
                const val ORDER_AHEAD = "order ahead"
        }
}