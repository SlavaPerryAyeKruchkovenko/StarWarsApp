package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IStarship

@Entity
data class StarshipEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    var isLike: Int,
) {
    companion object {
        fun fromIStarship(starship: IStarship, isLike: Int = 0): StarshipEntity {
            return StarshipEntity(
                starship.id,
                starship.name,
                starship.model,
                starship.manufacturer,
                isLike
            )
        }
    }
}