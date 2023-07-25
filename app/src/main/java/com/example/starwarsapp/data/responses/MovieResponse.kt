package com.example.starwarsapp.data.responses

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title") override val name: String,
    @SerializedName("director") override val director: String,
    @SerializedName("producer") override val producer: String,
    @SerializedName("url") override val id: String,
): IMovie {
    companion object {
        fun fromIMovie(movie: IMovie): MovieResponse {
            return MovieResponse(movie.name, movie.director, movie.producer,movie.id)
        }
    }
}