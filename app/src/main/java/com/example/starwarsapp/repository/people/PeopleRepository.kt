package com.example.starwarsapp.repository.people

import android.util.Log
import com.example.starwarsapp.data.interfaces.ICharacter
import com.example.starwarsapp.data.interfaces.IPilot
import com.example.starwarsapp.data.responses.CharacterResponse
import com.example.starwarsapp.data.responses.MovieResponse
import com.example.starwarsapp.data.responses.PeopleResponse
import com.example.starwarsapp.data.responses.PilotResponse
import com.example.starwarsapp.repository.interfaces.IPeopleRepository
import com.example.starwarsapp.repository.movie.MovieRepository
import com.example.starwarsapp.repository.utils.Converter

class PeopleRepository : IPeopleRepository {
    override suspend fun getPeoplesByName(name: String): List<ICharacter> {
        val networkRepository = PeopleNetworkRepository()

        suspend fun getNext(next: String): List<PeopleResponse> {
            val page = Converter.convertUrlToPage(next)
            val res = networkRepository.getPeoplesByNamePage(name, page)
            if (res.isSuccessful) {
                val response = res.body()!!
                return if (response.next != null) {
                    response.peoples + getNext(response.next)
                } else {
                    response.peoples
                }
            } else {
                throw Exception("get page:${page} data error")
            }
        }

        suspend fun getMovies(urls: List<String>): List<MovieResponse> {
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
        return try {
            val res = networkRepository.getPeoplesByName(name)
            if (res.isSuccessful) {
                val response = res.body()!!
                val resPeoples = response.peoples.toMutableList()

                if (response.next != null) {
                    resPeoples += getNext(response.next)
                }

                val peoples = resPeoples.map {
                    CharacterResponse(
                        Converter.convertUrlToId(it.id).toString(),
                        it.name,
                        it.sex,
                        it.starships.count(),
                        getMovies(it.films)
                    )
                }
                peoples
            } else {
                listOf()
            }
        } catch (e: Exception) {
            Log.e("get peoples by name error", e.toString())
            listOf()
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