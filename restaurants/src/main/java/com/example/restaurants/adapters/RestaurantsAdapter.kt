package com.example.restaurants.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Restaurant
import com.example.restaurants.adapters.viewHolders.RestaurantsViewHolder
import com.example.restaurants.utils.FavoriteRestaurantsCallback
import javax.inject.Inject

class RestaurantsAdapter @Inject constructor() : RecyclerView.Adapter<RestaurantsViewHolder>() {
    private val listOfRestaurants = mutableListOf<Restaurant>()
    var favoriteRestaurantsCallback: FavoriteRestaurantsCallback? = null

    fun setRestaurants(items: List<Restaurant>) {
        listOfRestaurants.clear()
        listOfRestaurants.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        return RestaurantsViewHolder.create(parent, favoriteRestaurantsCallback ?: {})
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.bind(listOfRestaurants[position])
    }

    override fun getItemCount(): Int = listOfRestaurants.size
}