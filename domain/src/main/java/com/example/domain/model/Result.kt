package com.example.domain.model

data class Result ( val restaurant: List<Restaurant>, val error: Throwable? = null)