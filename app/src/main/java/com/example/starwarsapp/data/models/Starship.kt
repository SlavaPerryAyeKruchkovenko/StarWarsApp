package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IStarship

data class Starship(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<Pilot>,
    override val films: List<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(id,isLiked), IStarship {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Starship
        return id == other.id && isLike == other.isLike
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + manufacturer.hashCode()
        result = 31 * result + pilots.hashCode()
        result = 31 * result + films.hashCode()
        return result
    }

    companion object {
        fun fromIStarship(starship: IStarship): Starship {
            val pilots = starship.pilots.map {
                Pilot.fromIPilot(it)
            }
            val films = starship.films.map {
                Movie.fromIMovie(it)
            }
            return Starship(
                starship.id,
                starship.name,
                starship.model,
                starship.manufacturer,
                pilots,
                films
            )
        }
    }
}