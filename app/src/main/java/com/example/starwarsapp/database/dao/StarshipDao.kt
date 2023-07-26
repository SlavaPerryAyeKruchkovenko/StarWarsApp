package com.example.starwarsapp.database.dao

import androidx.room.*
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PilotEntity
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

    @Query("UPDATE StarshipEntity SET isLike = 0 WHERE isLike >= 1")
    suspend fun dislikeAllStarships()

    @Transaction
    suspend fun softInsertStarships(starships: List<IStarship>) {
        starships.forEach { starship ->
            val dbStarship = getStarshipById(starship.id)
            val movies = starship.films.map {
                MovieEntity.fromIMovie(it)
            }
            val pilots = starship.pilots.map {
                PilotEntity.fromIPilot(it)
            }
            if (dbStarship != null) {
                deleteStarship(dbStarship.starship, dbStarship.movies, dbStarship.pilots)
                insertStarship(
                    StarshipEntity.fromIStarship(starship, dbStarship.starship.isLike),
                    movies,
                    pilots
                )
            } else {
                insertStarship(StarshipEntity.fromIStarship(starship), movies, pilots)
            }
        }
    }

    @Delete
    suspend fun deleteStarship(
        starship: StarshipEntity,
        movie: List<MovieEntity>,
        pilot: List<PilotEntity>
    )

    @Update
    suspend fun updateStarship(starship: StarshipEntity)

    @Insert
    suspend fun insertStarship(
        starship: StarshipEntity,
        movie: List<MovieEntity>,
        pilot: List<PilotEntity>
    )
}