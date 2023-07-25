package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.ICharacter
import com.example.starwarsapp.data.interfaces.IPilot
import com.example.starwarsapp.data.interfaces.IStarship

interface IPeopleRepository {
    suspend fun getPeoplesByName(name: String): List<ICharacter>
    suspend fun getPilotById(id: Int): IPilot?
}