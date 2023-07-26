package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.models.Movie
import com.example.starwarsapp.data.models.Planet

@Entity
data class PlanetEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val diameter: String,
    val population: String,
    var isLike: Int,
) {
    companion object {
        fun fromIPlanet(planet: IPlanet, isLiked: Int = 0): PlanetEntity {
            return PlanetEntity(
                planet.id,
                planet.name,
                planet.diameter,
                planet.population,
                isLiked
            )
        }
    }
}
