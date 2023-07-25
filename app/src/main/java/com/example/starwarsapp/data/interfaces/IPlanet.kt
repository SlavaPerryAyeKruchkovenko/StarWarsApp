package com.example.starwarsapp.data.interfaces

import com.example.starwarsapp.data.models.Movie

interface IPlanet {
    val id: String
    val name: String
    val diameter: Int
    val population: Long
    val films: List<IMovie>
}