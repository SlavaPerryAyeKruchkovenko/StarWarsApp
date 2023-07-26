package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.database.entity.MovieEntity

data class MovieDB(
    override val id: String,
    override val name: String,
    override val director: String,
    override val producer: String
) : IMovie {
    companion object {
        fun fromMovieEntity(movie: MovieEntity): MovieDB {
            return MovieDB(
                movie.id,
                movie.name,
                movie.director,
                movie.producer,
            )
        }
    }
}