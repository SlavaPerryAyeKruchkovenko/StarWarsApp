package com.example.starwarsapp.repository.starship

import com.example.starwarsapp.StarWarsApp
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.relation.StarshipWithMoviesAndPilot
import com.example.starwarsapp.database.repositories.IStarshipLocalRepository

class StarshipLocalRepository : IStarshipLocalRepository {
    override suspend fun getStarshipsByName(name: String): List<StarshipWithMoviesAndPilot> {
        val db = StarWarsApp.getDatabase()
        return db?.starshipDao()?.getStarshipsByName(name) ?: listOf()
    }

    override suspend fun getLikedStarships(): List<StarshipWithMoviesAndPilot> {
        val db = StarWarsApp.getDatabase()
        return db?.starshipDao()?.getLikedStarships() ?: listOf()
    }

    override suspend fun addStarships(starships: List<IStarship>) {
        val db = StarWarsApp.getDatabase()
        db?.starshipDao()?.softInsertStarships(starships)
    }

    override suspend fun update(starship: IStarship) {
        val db = StarWarsApp.getDatabase()
        val dbStarship = db?.starshipDao()?.getStarshipById(starship.id)
        if (dbStarship != null) {
            db.starshipDao().updateStarship(dbStarship.starship)
        }
    }

    override suspend fun dislikeAllStarships() {
        val db = StarWarsApp.getDatabase()
        db?.starshipDao()?.dislikeAllStarships()
    }
}