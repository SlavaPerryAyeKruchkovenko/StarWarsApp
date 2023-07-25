package com.example.starwarsapp.data.interfaces

interface ICharacter {
    val id: String
    val name: String
    val sex: String
    val starshipsCount: Int
    val films: List<IMovie>
}