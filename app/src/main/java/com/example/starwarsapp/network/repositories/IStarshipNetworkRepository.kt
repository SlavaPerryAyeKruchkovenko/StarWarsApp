package com.example.starwarsapp.network.repositories

import com.example.starwarsapp.network.responses.StarshipsListResponse
import retrofit2.Response

interface IStarshipNetworkRepository {
    suspend fun getStarshipsByName(name:String): Response<StarshipsListResponse>
    suspend fun getStarshipsByNamePage(name:String,page:Int): Response<StarshipsListResponse>
}