package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("name") val name: String,
    @SerializedName("diameter") val diameter: Int,
    @SerializedName("population") val population: Long,
    @SerializedName("films") val films: List<String>,
    @SerializedName("url") val id: String,
)