package com.example.starwarsapp.database.entity

import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPlanet

data class PlanetEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>,
    var isLike: Int,
): IPlanet
