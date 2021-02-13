package com.example.restaurants.adapters.viewHolders

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Restaurant
import com.example.restaurants.R
import com.example.restaurants.databinding.ItemRestaurantsBinding
import com.example.restaurants.utils.FavoriteRestaurantsCallback
import java.util.*

class RestaurantsViewHolder(
    private val binding: ItemRestaurantsBinding,
    private val favoriteRestaurantsCallback: FavoriteRestaurantsCallback
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Restaurant) {
        val iconDrawable = setFavoriteIconColor(item)
        item.run {
            binding.restaurantsName.text = name
            binding.restaurantsStatus.text = status.capitalize(Locale.ROOT)
            binding.restaurantsAveragePrice.text = "${sortString}: ${displaySortValue()} "
            binding.restaurantsStatus.setTextColor(setStatusColor(item.status))
            binding.favoriteRestaurants.setImageDrawable(iconDrawable)
        }
        binding.favoriteRestaurants.setOnClickListener { favoriteRestaurantsCallback.invoke(item) }
    }

    private fun setStatusColor(status: String): Int {
        return when (status) {
            Restaurant.CLOSED -> {
                itemView.resources.getColor(R.color.colorPrimaryDark, null)
            }
            Restaurant.OPEN -> {
                itemView.resources.getColor(R.color.colorAccent, null)
            }
            else -> {
                itemView.resources.getColor(R.color.colorTundora, null)
            }
        }
    }

    private fun setFavoriteIconColor(restaurant: Restaurant): Drawable? {
        return when {
            restaurant.isFavorite -> {
                ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite_red, null)
            }
            else -> ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite_black, null)
        }
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