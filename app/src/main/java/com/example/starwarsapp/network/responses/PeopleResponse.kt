package com.example.starwarsapp.network.responses

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("name") val name: String,
    @SerializedName("gender") val sex: String,
    @SerializedName("starships") val starships: List<String>,
    @SerializedName("films") val films: List<String>,
    @SerializedName("url") val id: String,
)