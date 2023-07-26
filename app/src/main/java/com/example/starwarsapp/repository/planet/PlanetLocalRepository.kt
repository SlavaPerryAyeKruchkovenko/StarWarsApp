package com.example.starwarsapp.repository.planet

import com.example.starwarsapp.StarWarsApp
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.database.relation.PlanetWithMovies
import com.example.starwarsapp.database.repositories.IPlanetLocalRepository

class PlanetLocalRepository : IPlanetLocalRepository {
    override suspend fun getPlanetsByName(name: String): List<PlanetWithMovies> {
        val db = StarWarsApp.getDatabase()
        return db?.planetDao()?.getPlanetsByName(name) ?: listOf()
    }

    override suspend fun getLikedPlanets(): List<PlanetWithMovies> {
        val db = StarWarsApp.getDatabase()
        return db?.planetDao()?.getLikedPlanets() ?: listOf()
    }

    override suspend fun addPlanets(planets: List<IPlanet>) {
        val db = StarWarsApp.getDatabase()
        db?.planetDao()?.softInsertPlanets(planets)
    }

    override suspend fun dislike(planet: IPlanet) {
        val db = StarWarsApp.getDatabase()
        val dbPlanet = db?.planetDao()?.getPlanetById(planet.id)
        if (dbPlanet != null) {
            db.planetDao().updatePlanet(dbPlanet.planet)
        }
    }

    override suspend fun dislikeAllPlanets() {
        val db = StarWarsApp.getDatabase()
        db?.planetDao()?.dislikeAllPlanets()
    }
}