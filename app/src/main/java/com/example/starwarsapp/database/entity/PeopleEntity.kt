package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPeople

@Entity
data class PeopleEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>,
    var isLike: Int,
) : IPeople {
    companion object {
        fun fromIPeople(people: IPeople, isLiked: Int = 0): PeopleEntity {
            val films = people.films.map {
                MovieEntity.fromIMovie(it)
            }
            return PeopleEntity(
                people.id,
                people.name,
                people.sex,
                people.starshipsCount,
                films,
                isLiked
            )
        }
    }
}