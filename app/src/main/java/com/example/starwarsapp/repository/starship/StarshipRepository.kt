package com.example.starwarsapp.repository.starship

import android.util.Log
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.database.models.PlanetDB
import com.example.starwarsapp.database.models.StarshipDB
import com.example.starwarsapp.network.models.StarshipImpResponse
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.network.responses.PilotResponse
import com.example.starwarsapp.network.responses.StarshipResponse
import com.example.starwarsapp.repository.interfaces.IStarshipRepository
import com.example.starwarsapp.repository.movie.MovieRepository
import com.example.starwarsapp.repository.people.PeopleRepository
import com.example.starwarsapp.repository.planet.PlanetLocalRepository
import com.example.starwarsapp.repository.utils.Converter

class StarshipRepository : IStarshipRepository {
    override suspend fun getStarshipsByName(name: String): List<IStarship> {
        val networkRepository = StarshipNetworkRepository()
        val localRepository = StarshipLocalRepository()
        return try {
            val res = networkRepository.getStarshipsByName(name)
            if (res.isSuccessful) {
                val response = res.body()!!
                val resStarships = response.starships.toMutableList()

                if (response.next != null) {
                    resStarships += getNext(response.next, networkRepository, name)
                }

                val starships = resStarships.map {
                    StarshipImpResponse(
                        Converter.convertUrlToId(it.id).toString(),
                        it.name,
                        it.model,
                        it.manufacturer,
                        getPilots(it.pilots),
                        getMovies(it.films)
                    )
                }
                localRepository.addStarships(starships)
                localRepository.getStarshipsByName(name).map {
                    StarshipDB.fromStarshipsWithMovieAndPilot(it)
                }
            } else {
                localRepository.getStarshipsByName(name).map {
                    StarshipDB.fromStarshipsWithMovieAndPilot(it)
                }
            }
        } catch (e: Exception) {
            Log.e("get starships by name error", e.toString())
            listOf()
        }
    }

    override suspend fun getLikedStarships(): List<IStarship> {
        val localRepository = StarshipLocalRepository()
        return try {
            localRepository.getLikedStarships().map {
                StarshipDB.fromStarshipsWithMovieAndPilot(it)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun updateStarship(starship: IStarship) {
        val localRepository = StarshipLocalRepository()
        try {
            localRepository.update(starship)
        } catch (_: Exception) {

        }
    }

    override suspend fun dislikeAllStarships(): List<IStarship> {
        val localRepository = StarshipLocalRepository()
        return try {
            localRepository.dislikeAllStarships()
            return localRepository.getLikedStarships().map {
                StarshipDB.fromStarshipsWithMovieAndPilot(it)
            }
        } catch (_: Exception) {
            listOf()
        }
    }

    private suspend fun getNext(
        next: String,
        networkRepository: StarshipNetworkRepository,
        name: String
    ): List<StarshipResponse> {
        val page = Converter.convertUrlToPage(next)
        val res = networkRepository.getStarshipsByNamePage(name, page)
        if (res.isSuccessful) {
            val response = res.body()!!
            return if (response.next != null) {
                response.starships + getNext(response.next, networkRepository, name)
            } else {
                response.starships
            }
        } else {
            throw Exception("get page:${page} data error")
        }
    }

    private suspend fun getMovies(urls: List<String>): List<MovieResponse> {
        return urls.map {
            val id = Converter.convertUrlToId(it)
            val movie = MovieRepository().getMovieById(id)
            if (movie != null) {
                MovieResponse.fromIMovie(movie)
            } else {
                throw Exception("movie:${id} not found")
            }
        }
    }

    private suspend fun getPilots(urls: List<String>): List<PilotResponse> {
        return urls.map {
            val id = Converter.convertUrlToId(it)
            val pilot = PeopleRepository().getPilotById(id)
            if (pilot != null) {
                PilotResponse.fromIPilot(pilot)
            } else {
                throw Exception("movie:${id} not found")
            }
        }
    }
}