package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IStarship

data class StarshipDB(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<PilotDB>,
    override val films: List<MovieDB>,
    override val isLiked: Boolean = false
) : IStarship