package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IMovie

@Entity
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val id: String,
    @ColumnInfo(name = "movie_name") val name: String,
    val director: String,
    val producer: String
) {
    companion object {
        fun fromIMovie(movie: IMovie): MovieEntity {
            return MovieEntity(movie.id, movie.name, movie.director, movie.producer)
        }
    }
}