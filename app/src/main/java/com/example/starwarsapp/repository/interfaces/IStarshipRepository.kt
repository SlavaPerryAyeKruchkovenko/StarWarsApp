package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IStarship

interface IStarshipRepository {
    suspend fun getStarshipsByName(name: String): List<IStarship>
}