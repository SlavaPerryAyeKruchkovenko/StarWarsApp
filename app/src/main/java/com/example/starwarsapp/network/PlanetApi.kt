package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.PlanetListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {
    @GET("planets/?search={name}/")
    suspend fun getPlanetsByName(@Path("name") name: String): Response<PlanetListResponse>
}