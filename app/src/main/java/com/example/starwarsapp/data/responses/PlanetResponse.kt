package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("name") val name: String,
    @SerializedName("diameter") val diameter: String,
    @SerializedName("population") val population: String,
    @SerializedName("films") val films: List<String>,
    @SerializedName("url") val id: String,
)