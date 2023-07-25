package com.example.starwarsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val url = "https://swapi.dev/api/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieApi = getRetrofit().create(MovieApi::class.java)
    val peopleApi: PeopleApi = getRetrofit().create(PeopleApi::class.java)
    val planetApi: PlanetApi = getRetrofit().create(PlanetApi::class.java)
    val starshipApi: StarshipApi = getRetrofit().create(StarshipApi::class.java)
}