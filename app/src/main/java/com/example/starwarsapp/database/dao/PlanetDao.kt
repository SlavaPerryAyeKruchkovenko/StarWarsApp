package com.example.starwarsapp.database.dao

import androidx.room.*
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PlanetEntity
import com.example.starwarsapp.database.relation.PlanetWithMovies

@Dao
interface PlanetDao {
    @Query("Select * from PlanetEntity WHERE id = :id")
    suspend fun getPlanetById(id: String): PlanetWithMovies?
    @Query("SELECT * FROM PlanetEntity WHERE LOWER(name) LIKE '%' || LOWER(:name) || '%'")
    suspend fun getPlanetsByName(name: String): List<PlanetWithMovies>

    @Query("SELECT * FROM PlanetEntity WHERE isLike = 1")
    suspend fun getLikedPlanets(): List<PlanetWithMovies>

    @Query("UPDATE PlanetEntity SET isLike = 0 WHERE isLike >= 1")
    suspend fun dislikeAllPlanets()

    @Transaction
    suspend fun softInsertPlanets(planets: List<IPlanet>) {
        planets.forEach { planet ->
            val dbPlanet = getPlanetById(planet.id)
            val movies = planet.films.map {
                MovieEntity.fromIMovie(it)
            }
            if (dbPlanet != null) {
                deletePlanet(dbPlanet.planet, dbPlanet.movies)
                insertPlanet(
                    PlanetEntity.fromIPlanet(planet, dbPlanet.planet.isLike),
                    movies
                )
            } else {
                insertPlanet(PlanetEntity.fromIPlanet(planet), movies)
            }
        }
    }

    @Delete
    suspend fun deletePlanet(
        people: PlanetEntity,
        movie: List<MovieEntity>
    )

    @Update
    suspend fun updatePlanet(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity, movie: List<MovieEntity>)
}