package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.interfaces.Likeable

data class Planet(
    override val id: String,
    override val name: String,
    override val diameter: Int,
    override val population: Long,
    override val films: Array<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(id), IPlanet, Likeable {
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

        return id == (other as Planet).id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + diameter
        result = 31 * result + population.hashCode()
        result = 31 * result + films.contentHashCode()
        return result
    }

}