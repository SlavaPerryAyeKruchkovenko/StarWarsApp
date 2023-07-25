package com.example.starwarsapp.data.interfaces

import com.example.starwarsapp.data.models.Movie

interface ICharacter {
    val name: String
    val sex: String
    val starshipsCount: Int
    val films: List<IMovie>
}