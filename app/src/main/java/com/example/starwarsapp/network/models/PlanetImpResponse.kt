package com.example.starwarsapp.network.models

import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.network.responses.MovieResponse

data class PlanetImpResponse(
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    override val films: List<MovieResponse>,
    override val isLiked: Boolean = false,
) : IPlanet