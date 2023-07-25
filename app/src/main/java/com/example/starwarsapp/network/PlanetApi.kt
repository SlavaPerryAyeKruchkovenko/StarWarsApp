package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.PlanetListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {
    @GET("planets/")
    suspend fun getPlanetsByName(@Query("search") name: String): Response<PlanetListResponse>

    @GET("planets/")
    suspend fun getPlanetsByNamePage(
        @Query("search") name: String,
        @Query("page") page: Int
    ): Response<PlanetListResponse>
}