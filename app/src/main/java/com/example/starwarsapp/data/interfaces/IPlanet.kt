package com.example.starwarsapp.data.interfaces

interface IPlanet {
    val id: String
    val name: String
    val diameter: String
    val population: String
    val films: List<IMovie>
    val isLiked: Boolean
}