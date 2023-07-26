package com.example.starwarsapp.network.repositories

import com.example.starwarsapp.network.responses.PlanetListResponse
import retrofit2.Response

interface IPlanetNetworkRepository {
    suspend fun getPlanetsByName(name: String): Response<PlanetListResponse>
    suspend fun getPlanetsByNamePage(name: String, page: Int): Response<PlanetListResponse>
}