package com.example.starwarsapp.data.interfaces

interface IPeople {
    val id: String
    val name: String
    val sex: String
    val starshipsCount: Int
    val films: List<IMovie>
}