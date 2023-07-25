package com.example.starwarsapp.repository.starship

import com.example.starwarsapp.data.responses.StarshipsListResponse
import com.example.starwarsapp.network.RetrofitBuilder
import com.example.starwarsapp.network.repositories.IStarshipNetworkRepository
import retrofit2.Response

class StarshipNetworkRepository : IStarshipNetworkRepository {
    override suspend fun getStarshipsByName(name: String): Response<StarshipsListResponse> {
        return RetrofitBuilder.starshipApi.getStarshipsByName(name)
    }

    override suspend fun getStarshipsByNamePage(
        name: String,
        page: Int
    ): Response<StarshipsListResponse> {
        return RetrofitBuilder.starshipApi.getStarshipsByNamePage(name, page)
    }
}