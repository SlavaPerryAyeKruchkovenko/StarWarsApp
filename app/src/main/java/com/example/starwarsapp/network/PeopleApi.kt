package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.PeopleResponse
import com.example.starwarsapp.data.responses.PeoplesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {
    @GET("people/")
    suspend fun getPeoplesByName(@Query("search") name: String): Response<PeoplesListResponse>

    @GET("people/")
    suspend fun getPeoplesByNamePage(
        @Query("search") name: String,
        @Query("page") page: Int
    ): Response<PeoplesListResponse>

    @GET("people/{id}")
    suspend fun getPeopleById(@Path("id") id: Int): Response<PeopleResponse>
}