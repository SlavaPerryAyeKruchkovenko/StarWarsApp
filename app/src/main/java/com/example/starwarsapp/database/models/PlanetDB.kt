package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.interfaces.IPlanet

data class PlanetDB(
    override val id: String,
    override val name: String,
    override val diameter: String,
    override val population: String,
    override val films: List<IMovie>,
    override val isLiked: Boolean = false
) : IPlanet