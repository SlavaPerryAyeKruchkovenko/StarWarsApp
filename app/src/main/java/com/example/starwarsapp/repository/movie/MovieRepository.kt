package com.example.starwarsapp.repository.movie

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.responses.MovieResponse
import com.example.starwarsapp.repository.interfaces.IMovieRepository

class MovieRepository : IMovieRepository {
    override suspend fun getMovieById(id: Int): IMovie? {
        val networkRepository = MovieNetworkRepository()
        val res = networkRepository.getMovieById(id)
        return if (res.isSuccessful) {
            val movie = res.body()!!
            MovieResponse(movie.name, movie.director, movie.producer, id.toString())
        } else {
            null
        }
    }
}