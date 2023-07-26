package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IPeople

data class PeopleDB(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipsCount: Int,
    override val films: List<MovieDB>,
    override val isLiked: Boolean = false
) : IPeople