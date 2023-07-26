package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.ICharacter

@Entity
data class PersonEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    @Relation(parentColumn = "id", entityColumn = "movie_id", entity = MovieEntity::class)
    override val films: List<MovieEntity>,
    var isLike: Int,
) : ICharacter