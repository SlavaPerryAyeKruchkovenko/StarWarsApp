package com.example.starwarsapp.repository.interfaces

import com.example.starwarsapp.data.interfaces.IMovie

interface IMovieRepository {
    suspend fun getMovieById(id: Int): IMovie?
}