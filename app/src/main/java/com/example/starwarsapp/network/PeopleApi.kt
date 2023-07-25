package com.example.starwarsapp.network

import com.example.starwarsapp.data.responses.PeopleResponse
import com.example.starwarsapp.data.responses.PeoplesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApi {
    @GET("people/?search={name}/")
    suspend fun getPeoplesByName(@Path("name") name: String): Response<PeoplesListResponse>

    @GET("people/?search={name}&page?={page}/")
    suspend fun getPeoplesByNamePage(
        @Path("name") name: String,
        @Path("page") page: Int
    ): Response<PeoplesListResponse>

    @GET("people/{id}/")
    suspend fun getPeopleById(@Path("id") id: Int): Response<PeopleResponse>
}