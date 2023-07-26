package com.example.starwarsapp.database.entity

import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.models.Movie
import com.example.starwarsapp.data.models.Planet

data class PlanetEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>,
    var isLike: Int,
): IPlanet {
    companion object {
        fun fromIPlanet(planet: IPlanet, isLiked: Int = 0): PlanetEntity {
            val films = planet.films.map {
                MovieEntity.fromIMovie(it)
            }
            return PlanetEntity(
                planet.id,
                planet.name,
                planet.diameter,
                planet.population,
                films,
                isLiked
            )
        }
    }
}
