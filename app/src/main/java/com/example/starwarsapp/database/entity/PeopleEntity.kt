package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.data.models.Character
import com.example.starwarsapp.data.models.Movie

@Entity
data class PeopleEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val sex: String,
    val starshipsCount: Int,
    var isLike: Int,
) {
    companion object {
        fun fromIPeople(people: IPeople, isLiked: Int = 0): PeopleEntity {
            return PeopleEntity(
                people.id,
                people.name,
                people.sex,
                people.starshipsCount,
                isLiked
            )
        }
    }
}