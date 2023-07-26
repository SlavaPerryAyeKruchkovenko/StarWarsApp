package com.example.starwarsapp.repository.people

import com.example.starwarsapp.StarWarsApp
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.database.relation.PeopleWithMovies
import com.example.starwarsapp.database.repositories.IPeopleLocalRepository

class PeopleLocalRepository : IPeopleLocalRepository {
    override suspend fun getPeoplesByName(name: String): List<PeopleWithMovies> {
        val db = StarWarsApp.getDatabase()
        return db?.peopleDao()?.getPeoplesByName(name) ?: listOf()
    }

    override suspend fun getLikedPeoples(): List<PeopleWithMovies> {
        val db = StarWarsApp.getDatabase()
        return db?.peopleDao()?.getLikedPeoples() ?: listOf()
    }

    override suspend fun addPeoples(peoples: List<IPeople>) {
        val db = StarWarsApp.getDatabase()
        db?.peopleDao()?.softInsertPeoples(peoples)
    }

    override suspend fun update(people: IPeople) {
        val db = StarWarsApp.getDatabase()
        val dbPeople = db?.peopleDao()?.getPeopleById(people.id)
        if (dbPeople != null) {
            db.peopleDao().updatePeople(dbPeople.people)
        }
    }

    override suspend fun dislikeAllPeoples() {
        val db = StarWarsApp.getDatabase()
        db?.peopleDao()?.dislikeAllPeoples()
    }
}