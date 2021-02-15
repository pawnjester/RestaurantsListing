package com.example.restaurants.models

import com.example.domain.model.SortOptionsObject.AVERAGE_PRODUCT_PRICE
import com.example.domain.model.SortOptionsObject.BEST_MATCH
import com.example.domain.model.SortOptionsObject.DELIVERY_COST
import com.example.domain.model.SortOptionsObject.DISTANCE
import com.example.domain.model.SortOptionsObject.MINIMUM_COST
import com.example.domain.model.SortOptionsObject.NEWEST
import com.example.domain.model.SortOptionsObject.POPULARITY
import com.example.domain.model.SortOptionsObject.RATING_AVERAGE


data class SortOption(val option: String, var isSelected: Boolean = false) {
    companion object {
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