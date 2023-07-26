package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IStarship

interface IStarshipRepository {
    suspend fun getStarshipsByName(name: String): List<IStarship>
    suspend fun getLikedStarships(): List<IStarship>
    suspend fun updateStarship(starship: IStarship)
    suspend fun dislikeAllStarships(): List<IStarship>
}