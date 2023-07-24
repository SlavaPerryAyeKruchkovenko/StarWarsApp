package com.example.starwarsapp.data.interfaces

import com.example.starwarsapp.data.models.Movie

interface ICharacter {
    val id: String
    val name: String
    val sex: String
    val starshipsCount: Int
    val films: List<Movie>
}