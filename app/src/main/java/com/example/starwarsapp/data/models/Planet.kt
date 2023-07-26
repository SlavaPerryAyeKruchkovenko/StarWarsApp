package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IPlanet

data class Planet(
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    override val films: List<Movie>,
    override val isLiked: Boolean = false
) : StarWarsObject(isLiked), IPlanet {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Planet
        return id == other.id && isLike == other.isLike
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + diameter.hashCode()
        result = 31 * result + population.hashCode()
        result = 31 * result + films.hashCode()
        return result
    }

    companion object {
        fun fromIPlanet(planet: IPlanet, isLiked: Boolean = false): Planet {
            val films = planet.films.map {
                Movie.fromIMovie(it)
            }
            return Planet(
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