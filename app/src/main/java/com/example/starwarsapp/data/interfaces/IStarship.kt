package com.example.starwarsapp.data.interfaces

interface IStarship {
    val name: String
    val model: String
    val manufacturer: String
    val pilots: List<IPilot>
    val films: List<IMovie>
}