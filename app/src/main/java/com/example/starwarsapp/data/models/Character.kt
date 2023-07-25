package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.ICharacter
import com.example.starwarsapp.data.interfaces.Likeable

data class Character(
    private val _id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(_id,isLiked), ICharacter {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return id == (other as Character).id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + sex.hashCode()
        result = 31 * result + films.hashCode()
        return result
    }
}