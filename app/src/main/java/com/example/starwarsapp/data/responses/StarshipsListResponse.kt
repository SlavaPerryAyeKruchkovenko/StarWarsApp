package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class StarshipsListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val starships: List<StarshipResponse>
)