package com.example.starwarsapp.repository.starship

import android.util.Log
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.network.responses.PilotResponse
import com.example.starwarsapp.network.models.StarshipImpResponse
import com.example.starwarsapp.network.responses.StarshipResponse
import com.example.starwarsapp.repository.interfaces.IStarshipRepository
import com.example.starwarsapp.repository.movie.MovieRepository
import com.example.starwarsapp.repository.people.PeopleRepository
import com.example.starwarsapp.repository.utils.Converter

class StarshipRepository : IStarshipRepository {
    override suspend fun getStarshipsByName(name: String): List<IStarship> {
        val networkRepository = StarshipNetworkRepository()

        suspend fun getNext(next: String): List<StarshipResponse> {
            val page = Converter.convertUrlToPage(next)
            val res = networkRepository.getStarshipsByNamePage(name, page)
            if (res.isSuccessful) {
                val response = res.body()!!
                return if (response.next != null) {
                    response.starships + getNext(response.next)
                } else {
                    response.starships
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

        suspend fun getPilots(urls: List<String>): List<PilotResponse> {
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
        return try {
            val res = networkRepository.getStarshipsByName(name)
            if (res.isSuccessful) {
                val response = res.body()!!
                val resStarships = response.starships.toMutableList()

                if (response.next != null) {
                    resStarships += getNext(response.next)
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
                starships
            } else {
                listOf()
            }
        } catch (e: Exception) {
            Log.e("get starships by name error", e.toString())
            listOf()
        }
    }
}