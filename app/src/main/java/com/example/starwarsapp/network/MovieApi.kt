package com.example.starwarsapp.network

import com.example.starwarsapp.network.responses.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("films/{id}")
    suspend fun getFilmsById(@Path("id") id: Int): Response<MovieResponse>
}