package com.example.starwarsapp.repository.movie

import com.example.starwarsapp.data.responses.MovieResponse
import com.example.starwarsapp.network.RetrofitBuilder
import com.example.starwarsapp.network.repositories.IMovieNetworkRepository
import retrofit2.Response

class MovieNetworkRepository : IMovieNetworkRepository {
    override suspend fun getMovieById(id: Int): Response<MovieResponse> {
        return RetrofitBuilder.movieApi.getFilmsById(id)
    }
}