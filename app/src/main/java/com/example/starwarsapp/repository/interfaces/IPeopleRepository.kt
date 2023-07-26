package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.data.interfaces.IPilot

interface IPeopleRepository {
    suspend fun getPeoplesByName(name: String): List<IPeople>
    suspend fun getPilotById(id: Int): IPilot?
}