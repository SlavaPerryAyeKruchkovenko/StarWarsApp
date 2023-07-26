package com.example.starwarsapp.data.utils

import com.example.starwarsapp.R

enum class RequestType(val value: Int) {
    ALL(R.string.all), PLANETS(R.string.planets), STARSHIPS(R.string.starships_simple), CHARACTERS(R.string.characters)
}