package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.StarshipsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipApi {
    @GET("starships/?search={name}")
    suspend fun getStarshipsByName(@Path("name") name: String): Response<StarshipsListResponse>

    @GET("planets/?search={name}&page?={page}/")
    suspend fun getStarshipsByNamePage(
        @Path("name") name: String,
        @Path("page") page: Int
    ): Response<StarshipsListResponse>
}