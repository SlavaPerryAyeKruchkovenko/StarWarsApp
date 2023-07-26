package com.example.starwarsapp.repository.people

import android.util.Log
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.data.interfaces.IPilot
import com.example.starwarsapp.database.models.PeopleDB
import com.example.starwarsapp.network.models.PeopleImpResponse
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.network.responses.PeopleResponse
import com.example.starwarsapp.network.responses.PilotResponse
import com.example.starwarsapp.repository.interfaces.IPeopleRepository
import com.example.starwarsapp.repository.movie.MovieRepository
import com.example.starwarsapp.repository.utils.Converter

class PeopleRepository : IPeopleRepository {
    override suspend fun getPeoplesByName(name: String): List<IPeople> {
        val networkRepository = PeopleNetworkRepository()
        val localRepository = PeopleLocalRepository()
        return try {
            val res = networkRepository.getPeoplesByName(name)
            if (res.isSuccessful) {
                val response = res.body()!!
                val resPeoples = response.peoples.toMutableList()

                if (response.next != null) {
                    resPeoples += getNext(response.next, networkRepository, name)
                }

                val peoples = resPeoples.map {
                    PeopleImpResponse(
                        Converter.convertUrlToId(it.id).toString(),
                        it.name,
                        it.sex,
                        it.starships.count(),
                        getMovies(it.films)
                    )
                }
                localRepository.addPeoples(peoples)
                localRepository.getPeoplesByName(name).map {
                    PeopleDB.fromPeopleWithMovie(it)
                }
            } else {
                localRepository.getPeoplesByName(name).map {
                    PeopleDB.fromPeopleWithMovie(it)
                }
            }
        } catch (e: Exception) {
            Log.e("get peoples by name error", e.toString())
            listOf()
        }
    }

    override suspend fun getLikedPeoples(): List<IPeople> {
        val localRepository = PeopleLocalRepository()
        return try {
            localRepository.getLikedPeoples().map {
                PeopleDB.fromPeopleWithMovie(it)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun updatePeople(people: IPeople) {
        val localRepository = PeopleLocalRepository()
        try {
            localRepository.update(people)
        } catch (_: Exception) {

        }
    }

    override suspend fun dislikeAllPeoples(): List<IPeople> {
        val localRepository = PeopleLocalRepository()
        return try {
            localRepository.dislikeAllPeoples()
            return localRepository.getLikedPeoples().map {
                PeopleDB.fromPeopleWithMovie(it)
            }
        } catch (_: Exception) {
            listOf()
        }
    }

    private suspend fun getNext(
        next: String,
        networkRepository: PeopleNetworkRepository,
        name: String
    ): List<PeopleResponse> {
        val page = Converter.convertUrlToPage(next)
        val res = networkRepository.getPeoplesByNamePage(name, page)
        if (res.isSuccessful) {
            val response = res.body()!!
            return if (response.next != null) {
                response.peoples + getNext(response.next, networkRepository, name)
            } else {
                response.peoples
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

    override suspend fun getPilotById(id: Int): IPilot? {
        val networkRepository = PeopleNetworkRepository()
        return try {
            val res = networkRepository.getPeople(id)
            if (res.isSuccessful) {
                val pilot = res.body()!!
                PilotResponse(id.toString(), pilot.name, pilot.sex, pilot.starships.count())
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("get pilot by id error", e.toString())
            null
        }
    }
}