package com.example.starwarsapp.database.dao

import androidx.room.*
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.entity.StarshipEntity
import com.example.starwarsapp.database.relation.StarshipWithMoviesAndPilot

@Dao
interface StarshipDao {
    @Query("Select * from StarshipEntity WHERE id = :id")
    suspend fun getStarshipById(id: String): StarshipWithMoviesAndPilot?

    @Query("SELECT * FROM StarshipEntity WHERE name = :name")
    suspend fun getStarshipsByName(name: String): List<StarshipWithMoviesAndPilot>

    @Query("SELECT * FROM StarshipEntity WHERE isLike = 1")
    suspend fun getLikedStarships(): List<StarshipWithMoviesAndPilot>

    @Transaction
    suspend fun softInsertStarships(starships: List<IStarship>) {
        /*starships.forEach { starship ->
            val dbStarship = getStarshipById(starship.id)
            if (dbStarship != null) {
                updateStarship(StarshipEntity.fromIStarship(starship, dbStarship.isLike))
            } else {
                insertStarship(StarshipEntity.fromIStarship(starship))
            }
        }*/
    }

    @Update
    suspend fun updateStarship(starship: StarshipEntity)

    @Insert
    suspend fun insertStarship(starship: StarshipEntity)
}