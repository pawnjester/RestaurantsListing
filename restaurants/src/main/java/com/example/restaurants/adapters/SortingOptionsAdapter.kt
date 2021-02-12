package com.example.restaurants.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.adapters.viewHolders.SortingOptionViewHolder
import com.example.restaurants.models.SortOption
import com.example.restaurants.utils.SortOptionCallback
import javax.inject.Inject

class SortingOptionsAdapter @Inject constructor(
) : RecyclerView.Adapter<SortingOptionViewHolder>() {

    private val sortingOption = mutableListOf<SortOption>()

    var sortingOptionCallback: SortOptionCallback? = null

    fun setOptions(items: List<SortOption>) {
        sortingOption.clear()
        sortingOption.addAll(items)
        notifyDataSetChanged()
    }

    fun updateSortSelection(option : SortOption) {
        sortingOption.map { it.isSelected = it.option == option.option }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortingOptionViewHolder {
        return SortingOptionViewHolder.create(parent, sortingOptionCallback ?: {})
    }

    override fun onBindViewHolder(holder: SortingOptionViewHolder, position: Int) {
        holder.bind(sortingOption[position])
    }

    override fun getItemCount(): Int = sortingOption.size
}