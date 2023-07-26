package com.example.starwarsapp.database.entity

import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.data.models.Movie
import com.example.starwarsapp.data.models.Pilot
import com.example.starwarsapp.data.models.Starship

data class StarshipEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    @Relation(parentColumn = "id", entityColumn = "pilot_id", entity = PilotEntity::class)
    override val pilots: List<PilotEntity>,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>,
    var isLike: Int,
) : IStarship {
    companion object {
        fun fromIStarship(starship: IStarship,isLike: Int = 0): StarshipEntity {
            val pilots = starship.pilots.map {
                PilotEntity.fromIPilot(it)
            }
            val films = starship.films.map {
                MovieEntity.fromIMovie(it)
            }
            return StarshipEntity(
                starship.id,
                starship.name,
                starship.model,
                starship.manufacturer,
                pilots,
                films,
                isLike
            )
        }
    }
}