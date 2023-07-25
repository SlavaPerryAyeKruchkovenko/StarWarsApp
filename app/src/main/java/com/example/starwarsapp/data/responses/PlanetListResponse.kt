package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class PlanetListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val planets: List<PlanetResponse>
)