package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.ICharacter

data class Character(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<Movie>,
    private val isLiked: Boolean = false
) : StarWarsObject(id, isLiked), ICharacter {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Character
        return id == other.id && isLike == other.isLike
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + sex.hashCode()
        result = 31 * result + films.hashCode()
        return result
    }

    companion object {
        fun fromICharacter(character: ICharacter, isLiked: Boolean = false): Character {
            val films = character.films.map {
                Movie.fromIMovie(it)
            }
            return Character(
                character.id,
                character.name,
                character.sex,
                character.starshipsCount,
                films,
                isLiked
            )
        }
    }
}