package com.example.cache.room.converters

import androidx.room.TypeConverter
import com.example.cache.models.SortingValuesCacheModel
import com.google.gson.Gson

class Converter {

    companion object {
        @TypeConverter
        @JvmStatic fun sortingValuesToString(sortingValues: SortingValuesCacheModel): String {
            return Gson().toJson(sortingValues)
        }

        @TypeConverter
        @JvmStatic fun stringToSortingValues(stringSortingValues: String): SortingValuesCacheModel {
            return Gson().fromJson(stringSortingValues, SortingValuesCacheModel::class.java)
        }
    }
}