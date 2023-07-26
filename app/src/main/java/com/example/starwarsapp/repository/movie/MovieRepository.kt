package com.example.starwarsapp.repository.movie

import android.util.Log
import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.repository.interfaces.IMovieRepository

class MovieRepository : IMovieRepository {
    override suspend fun getMovieById(id: Int): IMovie? {
        val networkRepository = MovieNetworkRepository()
        return try {
            val res = networkRepository.getMovieById(id)
            if (res.isSuccessful) {
                val movie = res.body()!!
                MovieResponse(movie.name, movie.director, movie.producer, id.toString())
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("get movie by id error", e.toString())
            null
        }
    }
}