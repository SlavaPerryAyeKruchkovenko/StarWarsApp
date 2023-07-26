package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.database.relation.PeopleWithMovies

data class PeopleDB(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<MovieDB>,
    override val isLiked: Boolean = false
) : IPeople {
    companion object {
        fun fromPeopleWithMovie(people: PeopleWithMovies): PeopleDB {
            val films = people.movies.map {
                MovieDB.fromMovieEntity(it)
            }
            return PeopleDB(
                people.people.id,
                people.people.name,
                people.people.sex,
                people.people.starshipsCount,
                films,
                people.people.isLike > 0
            )
        }
    }
}