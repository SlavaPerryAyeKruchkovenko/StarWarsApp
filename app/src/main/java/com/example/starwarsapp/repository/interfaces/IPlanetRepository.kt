package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.interfaces.IStarship

interface IPlanetRepository {
    suspend fun getPlanetsByName(name: String): List<IPlanet>
}