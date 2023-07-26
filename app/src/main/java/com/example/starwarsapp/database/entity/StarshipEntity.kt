package com.example.starwarsapp.database.entity

import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IStarship

data class StarshipEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    @Relation(parentColumn = "id", entityColumn = "pilot_id", entity = PilotEntity::class)
    override val pilots: List<PilotEntity>,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>
) : IStarship