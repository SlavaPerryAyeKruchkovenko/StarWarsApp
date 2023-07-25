package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class StarshipResponse(
    @SerializedName("name") val name: String,
    @SerializedName("model") val model: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("films") val films: List<String>,
    @SerializedName("pilots") val pilots: List<String>,
    @SerializedName("url") val id: String,
)