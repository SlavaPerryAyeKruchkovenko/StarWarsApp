package com.example.starwarsapp.repository.planet

import android.util.Log
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.network.models.PlanetImpResponse
import com.example.starwarsapp.network.responses.PlanetResponse
import com.example.starwarsapp.repository.interfaces.IPlanetRepository
import com.example.starwarsapp.repository.movie.MovieRepository
import com.example.starwarsapp.repository.utils.Converter

class PlanetRepository : IPlanetRepository {
    override suspend fun getPlanetsByName(name: String): List<IPlanet> {
        val networkRepository = PlanetNetworkRepository()

        suspend fun getNext(next: String): List<PlanetResponse> {
            val page = Converter.convertUrlToPage(next)
            val res = networkRepository.getPlanetsByNamePage(name, page)
            if (res.isSuccessful) {
                val response = res.body()!!
                return if (response.next != null) {
                    response.planets + getNext(response.next)
                } else {
                    response.planets
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
            val res = networkRepository.getPlanetsByName(name)
            if (res.isSuccessful) {
                val response = res.body()!!
                val resPlanets = response.planets.toMutableList()

                if (response.next != null) {
                    resPlanets += getNext(response.next)
                }

                val planets = resPlanets.map {
                    PlanetImpResponse(
                        Converter.convertUrlToId(it.id).toString(),
                        it.name,
                        it.diameter,
                        it.population,
                        getMovies(it.films)
                    )
                }
                planets
            } else {
                listOf()
            }
        } catch (e: Exception) {
            Log.e("get planets by name error", e.toString())
            listOf()
        }
    }
}