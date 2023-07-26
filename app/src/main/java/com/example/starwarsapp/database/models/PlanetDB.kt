package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.database.relation.PeopleWithMovies
import com.example.starwarsapp.database.relation.PlanetWithMovies

data class PlanetDB(
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    override val films: List<IMovie>,
    override val isLiked: Boolean = false
) : IPlanet {
    companion object {
        fun fromPlanetWithMovie(planet: PlanetWithMovies): PlanetDB {
            val films = planet.movies.map {
                MovieDB.fromMovieEntity(it)
            }
            return PlanetDB(
                planet.planet.id,
                planet.planet.name,
                planet.planet.diameter,
                planet.planet.population,
                films,
                planet.planet.isLike > 0
            )
        }
    }
}