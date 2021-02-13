package com.example.restaurants.utils

import java.util.*

fun getGreetingForTheDay(): CharSequence {

    return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 12..16 -> "Good afternoon ☀️"
        in 7..12 -> "Good morning 🌤 "
        in 16..20 -> "Good evening 🌙"
        in 20..24 -> "Good night 😴 "
        in 0..7 -> "Bedtime 😴 "
        else -> "Welcome!"
    }
}

fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)
