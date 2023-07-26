package com.example.starwarsapp.repository.planet

import com.example.starwarsapp.network.responses.PlanetListResponse
import com.example.starwarsapp.network.RetrofitBuilder
import com.example.starwarsapp.network.repositories.IPlanetNetworkRepository
import retrofit2.Response

class PlanetNetworkRepository : IPlanetNetworkRepository {
    override suspend fun getPlanetsByName(name: String): Response<PlanetListResponse> {
        return RetrofitBuilder.planetApi.getPlanetsByName(name)
    }

    override suspend fun getPlanetsByNamePage(
        name: String,
        page: Int
    ): Response<PlanetListResponse> {
        return RetrofitBuilder.planetApi.getPlanetsByNamePage(name, page)
    }
}