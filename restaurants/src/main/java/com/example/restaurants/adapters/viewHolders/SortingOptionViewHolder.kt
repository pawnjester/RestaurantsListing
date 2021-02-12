package com.example.restaurants.adapters.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.R
import com.example.restaurants.databinding.ItemSortingBinding
import com.example.restaurants.models.SortOption
import com.example.restaurants.utils.SortOptionCallback

class SortingOptionViewHolder(
    private val binding: ItemSortingBinding,
    private val sortingOptionCallback: SortOptionCallback
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SortOption) {
        item.run {
            binding.name.text = option
        }

        binding.sortCardLayout.setOnClickListener {
            sortingOptionCallback.invoke(item)
        }

        if (item.isSelected) {
            binding.sortCardLayout.setBackgroundColor(backgroundColor(R.color.white))
            binding.name.setTextColor(backgroundColor(R.color.colorTundora))
        } else {
            binding.sortCardLayout.setBackgroundColor(backgroundColor(R.color.colorPrimary))
            binding.name.setTextColor(backgroundColor(R.color.white))

        }

    }

    private fun backgroundColor(color: Int) = itemView.resources.getColor(color, null)

    companion object {
        fun create(
            parent: ViewGroup,
            sortingOptionCallback: SortOptionCallback
        ): SortingOptionViewHolder {
            val binding = ItemSortingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SortingOptionViewHolder(binding, sortingOptionCallback)
        }
    }
}