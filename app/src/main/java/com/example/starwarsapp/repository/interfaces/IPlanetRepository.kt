package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IPlanet

interface IPlanetRepository {
    suspend fun getPlanetsByName(name: String): List<IPlanet>
    suspend fun getLikedPlanets(): List<IPlanet>
    suspend fun updatePlanet(planet: IPlanet)
    suspend fun dislikeAllPlanets(): List<IPlanet>
}