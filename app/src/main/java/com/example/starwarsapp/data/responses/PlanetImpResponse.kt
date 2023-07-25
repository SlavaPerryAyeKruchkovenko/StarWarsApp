package com.example.starwarsapp.data.responses

import com.example.starwarsapp.data.interfaces.IPlanet

data class PlanetImpResponse(
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    override val films: List<MovieResponse>
) : IPlanet