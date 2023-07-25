package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.PeoplesListResponse
import com.example.starwarsapp.data.responses.PlanetListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {
    @GET("planets/")
    suspend fun getPlanetsByName(@Path("search") name: String): Response<PlanetListResponse>
    @GET("planets/")
    suspend fun getPlanetsByNamePage(
        @Path("search") name: String,
        @Path("page") page: Int
    ): Response<PlanetListResponse>
}