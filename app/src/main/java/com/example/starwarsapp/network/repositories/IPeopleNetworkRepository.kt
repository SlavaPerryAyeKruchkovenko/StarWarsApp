package com.example.starwarsapp.network.repositories

import com.example.starwarsapp.network.responses.PeopleResponse
import com.example.starwarsapp.network.responses.PeoplesListResponse
import retrofit2.Response

interface IPeopleNetworkRepository {
    suspend fun getPeoplesByName(name:String): Response<PeoplesListResponse>
    suspend fun getPeoplesByNamePage(name:String,page:Int): Response<PeoplesListResponse>
    suspend fun getPeople(id: Int): Response<PeopleResponse>
}