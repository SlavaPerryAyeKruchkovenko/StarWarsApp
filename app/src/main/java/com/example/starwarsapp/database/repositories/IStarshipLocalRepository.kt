package com.example.starwarsapp.database.repositories

import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.relation.StarshipWithMoviesAndPilot

interface IStarshipLocalRepository {
    suspend fun getStarshipsByName(name: String): List<StarshipWithMoviesAndPilot>
    suspend fun getLikedStarships(): List<StarshipWithMoviesAndPilot>
    suspend fun addStarships(starships: List<IStarship>)
    suspend fun update(starship: IStarship)
    suspend fun dislikeAllStarships()
}