package com.example.restaurants.models

data class SortOption(val option: String, var isSelected: Boolean = false) {


    companion object {
        const val BEST_MATCH = "Best Match"
        const val NEWEST = "Newest"
        const val RATING_AVERAGE = "Rating Average"
        const val DISTANCE = "Distance"
        const val POPULARITY = "Popularity"
        const val AVERAGE_PRODUCT_PRICE = "Average Product Price"
        const val DELIVERY_COST = "Delivery Costs"
        const val MINIMUM_COST = "Minimum Cost"

        fun getSortItemsValue(): List<SortOption> {
            val items = mutableListOf<SortOption>()
            items.add(SortOption(BEST_MATCH))
            items.add(SortOption(NEWEST))
            items.add(SortOption(RATING_AVERAGE))
            items.add(SortOption(POPULARITY))
            items.add(SortOption(DISTANCE))
            items.add(SortOption(AVERAGE_PRODUCT_PRICE))
            items.add(SortOption(DELIVERY_COST))
            items.add(SortOption(MINIMUM_COST))
            return items
        }
    }
}