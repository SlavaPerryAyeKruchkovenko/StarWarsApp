package com.example.starwarsapp.network.responses

import com.google.gson.annotations.SerializedName

data class PeoplesListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val peoples: List<PeopleResponse>
)