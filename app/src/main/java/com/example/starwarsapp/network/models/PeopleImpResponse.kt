package com.example.starwarsapp.network.models

import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.network.responses.MovieResponse

data class PeopleImpResponse(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<MovieResponse>,
    override val isLiked: Boolean = false,
) : IPeople