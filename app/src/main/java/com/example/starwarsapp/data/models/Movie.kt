package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IMovie

data class Movie(
    override val id: String,
    override val name: String,
    override val director: String,
    override val producer: String,
): IMovie