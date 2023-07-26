package com.example.starwarsapp.database.dao

import androidx.room.*
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.database.entity.PlanetEntity
import com.example.starwarsapp.database.relation.PlanetWithMovies

@Dao
interface PlanetDao {
    @Query("Select * from PlanetEntity WHERE id = :id")
    suspend fun getPlanetById(id: String): PlanetWithMovies?
    @Query("SELECT * FROM PlanetEntity WHERE name = :name")
    suspend fun getPlanetsByName(name: String): List<PlanetWithMovies>

    @Query("SELECT * FROM PlanetEntity WHERE isLike = 1")
    suspend fun getLikedPlanets(): List<PlanetWithMovies>

    @Transaction
    suspend fun softInsertPlanets(planets: List<IPlanet>) {
        /*planets.forEach { planet ->
            val dbPlanet = getPlanetById(planet.id)
            if (dbPlanet != null) {
                updatePlanet(PlanetEntity.fromIPlanet(planet, dbPlanet.isLike))
            } else {
                insertPlanet(PlanetEntity.fromIPlanet(planet))
            }
        }*/
    }

    @Update
    suspend fun updatePlanet(planet: PlanetEntity)

    @Insert
    suspend fun insertPlanet(planet: PlanetEntity)
}