package com.example.starwarsapp.database.repositories

import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.database.relation.PeopleWithMovies

interface IPeopleLocalRepository {
    suspend fun getPeoplesByName(name: String): List<PeopleWithMovies>
    suspend fun getLikedPeoples(): List<PeopleWithMovies>
    suspend fun addPeoples(peoples: List<IPeople>)
    suspend fun update(people: IPeople)
    suspend fun dislikeAllPeoples()
}