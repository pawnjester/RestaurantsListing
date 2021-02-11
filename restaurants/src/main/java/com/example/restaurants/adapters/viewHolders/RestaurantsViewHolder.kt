package com.example.restaurants.adapters.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.databinding.ItemRestaurantsBinding
import com.example.restaurants.models.RestaurantModel
import com.example.restaurants.utils.FavoriteRestaurantsCallback

class RestaurantsViewHolder(
    private val binding: ItemRestaurantsBinding,
    private val favoriteRestaurantsCallback: FavoriteRestaurantsCallback
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RestaurantModel) {
        item.run {
            binding.restaurantsName.text = item.name
            binding.restaurantsStatus.text = item.status
            binding.restaurantsAveragePrice.text = item.sortingValues.averageProductPrice.toString()
        }

        binding.favoriteRestaurants.setOnClickListener { favoriteRestaurantsCallback.invoke(item) }

    }

    companion object {
        fun create(
            parent: ViewGroup,
            favoriteRestaurantsCallback: FavoriteRestaurantsCallback
        ): RestaurantsViewHolder {
            val binding = ItemRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RestaurantsViewHolder(binding, favoriteRestaurantsCallback)
        }
    }
}