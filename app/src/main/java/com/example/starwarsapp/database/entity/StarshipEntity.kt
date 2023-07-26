package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IStarship

@Entity
data class StarshipEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    var isLike: Int,
)