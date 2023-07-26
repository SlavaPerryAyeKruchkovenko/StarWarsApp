package com.example.starwarsapp.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PeopleEntity
import com.example.starwarsapp.database.entity.PlanetEntity

data class PeopleWithMovies(
    @Embedded
    val people: PeopleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id",
        entity = MovieEntity::class,
    )
    val movies: List<MovieEntity>
)