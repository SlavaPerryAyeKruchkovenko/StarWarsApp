package com.example.starwarsapp.network.repositories

import com.example.starwarsapp.network.responses.MovieResponse
import retrofit2.Response

interface IMovieNetworkRepository {
    suspend fun getMovieById(id: Int): Response<MovieResponse>
}