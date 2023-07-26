package com.example.starwarsapp.data.responses

import com.example.starwarsapp.data.interfaces.IPeople

data class PeopleImpResponse(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<MovieResponse>
) : IPeople