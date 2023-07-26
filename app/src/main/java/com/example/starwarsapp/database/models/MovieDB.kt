package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IMovie

data class MovieDB(
    override val id: String,
    override val name: String,
    override val director: String,
    override val producer: String
):IMovie