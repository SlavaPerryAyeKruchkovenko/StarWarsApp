package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.StarshipsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipApi {
    @GET("starships/")
    suspend fun getStarshipsByName(@Path("search") name: String): Response<StarshipsListResponse>

    @GET("planets/")
    suspend fun getStarshipsByNamePage(
        @Path("search") name: String,
        @Path("page") page: Int
    ): Response<StarshipsListResponse>
}