package com.example.starwarsapp.repository.people

import com.example.starwarsapp.data.responses.PeopleResponse
import com.example.starwarsapp.data.responses.PeoplesListResponse
import com.example.starwarsapp.network.RetrofitBuilder
import com.example.starwarsapp.network.repositories.IPeopleNetworkRepository
import retrofit2.Response

class PeopleNetworkRepository : IPeopleNetworkRepository {
    override suspend fun getPeoplesByName(name: String): Response<PeoplesListResponse> {
        return RetrofitBuilder.peopleApi.getPeoplesByName(name)
    }

    override suspend fun getPeoplesByNamePage(
        name: String,
        page: Int
    ): Response<PeoplesListResponse> {
        return RetrofitBuilder.peopleApi.getPeoplesByNamePage(name, page)
    }

    override suspend fun getPeople(id: Int): Response<PeopleResponse> {
        return RetrofitBuilder.peopleApi.getPeopleById(id)
    }
}