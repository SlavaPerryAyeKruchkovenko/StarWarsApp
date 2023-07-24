package com.example.starwarsapp.data.interfaces

import com.example.starwarsapp.data.models.Movie
import com.example.starwarsapp.data.models.Pilot

interface IStarship {
    val id: String
    val name: String
    val model: String
    val manufacturer: String
    val pilots: List<Pilot>
    val films: List<Movie>
}