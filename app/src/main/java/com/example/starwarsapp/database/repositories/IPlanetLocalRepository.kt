package com.example.starwarsapp.database.repositories

import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.database.relation.PlanetWithMovies

interface IPlanetLocalRepository {
    suspend fun getPlanetsByName(name: String): List<PlanetWithMovies>
    suspend fun getLikedPlanets(): List<PlanetWithMovies>
    suspend fun addPlanets(planets: List<IPlanet>)
    suspend fun update(planet: IPlanet)
    suspend fun dislikeAllPlanets()
}