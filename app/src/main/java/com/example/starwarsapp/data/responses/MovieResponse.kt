package com.example.starwarsapp.data.responses

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title") val name: String,
    @SerializedName("director") val director: String,
    @SerializedName("producer") val producer: String,
    @SerializedName("url") val id: String,
)