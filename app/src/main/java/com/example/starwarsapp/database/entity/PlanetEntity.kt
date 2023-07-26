package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPlanet

@Entity
data class PlanetEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val diameter: String,
    val population: String,
    var isLike: Int,
)
