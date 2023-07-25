package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.data.interfaces.Likeable

data class Starship(
    private val _id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<Pilot>,
    override val films: List<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(_id,isLiked), IStarship {
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
}