package com.example.starwarsapp.data.interfaces

import com.example.starwarsapp.data.models.Movie

interface IStarship {
    val id: String
    val name: String
    val model: String
    val manufacturer: String
    val pilots: Array<String>
    val films: Array<Movie>
}