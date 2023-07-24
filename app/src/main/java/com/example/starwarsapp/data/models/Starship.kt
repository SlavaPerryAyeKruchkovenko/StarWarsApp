package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.data.interfaces.Likeable

data class Starship(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: Array<String>,
    override val films: Array<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(id), IStarship, Likeable {
    var isLike = isLiked
        private set

    override fun dislike() {
        isLike = false
    }

    override fun like() {
        isLike = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return id == (other as Starship).id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + manufacturer.hashCode()
        result = 31 * result + pilots.contentHashCode()
        result = 31 * result + films.contentHashCode()
        return result
    }
}