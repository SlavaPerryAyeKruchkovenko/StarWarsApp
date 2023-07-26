package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.relation.StarshipWithMoviesAndPilot

data class StarshipDB(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<PilotDB>,
    override val films: List<MovieDB>,
    override val isLiked: Boolean = false
) : IStarship {
    companion object {
        fun fromStarshipsWithMovieAndPilot(starship: StarshipWithMoviesAndPilot): StarshipDB {
            val films = starship.movies.map {
                MovieDB.fromMovieEntity(it)
            }
            val pilots = starship.pilots.map {
                PilotDB.fromPilotEntity(it)
            }
            return StarshipDB(
                starship.starship.id,
                starship.starship.name,
                starship.starship.model,
                starship.starship.manufacturer,
                pilots,
                films,
                starship.starship.isLike > 0
            )
        }
    }
}