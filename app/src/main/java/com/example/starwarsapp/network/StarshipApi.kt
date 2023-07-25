package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.StarshipsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarshipApi {
    @GET("starships/")
    suspend fun getStarshipsByName(@Query("search") name: String): Response<StarshipsListResponse>

    @GET("planets/")
    suspend fun getStarshipsByNamePage(
        @Query("search") name: String,
        @Query("page") page: Int
    ): Response<StarshipsListResponse>
}