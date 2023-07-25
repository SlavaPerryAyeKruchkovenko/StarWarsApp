package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.StarshipsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipApi {
    @GET("starships/?search={name}")
    suspend fun getStarshipsByName(@Path("name") artifactId: String): Response<StarshipsListResponse>
}