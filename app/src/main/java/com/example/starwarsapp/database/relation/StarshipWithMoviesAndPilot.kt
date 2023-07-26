package com.example.starwarsapp.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PilotEntity
import com.example.starwarsapp.database.entity.StarshipEntity

data class StarshipWithMoviesAndPilot(
    @Embedded
    val starship: StarshipEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id",
        entity = MovieEntity::class
    )
    val movies: List<MovieEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "pilot_id",
        entity = PilotEntity::class
    )
    val pilots: List<PilotEntity>
)