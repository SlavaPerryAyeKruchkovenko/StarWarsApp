package com.example.starwarsapp.network.models

import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.network.responses.MovieResponse
import com.example.starwarsapp.network.responses.PilotResponse

class StarshipImpResponse(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<PilotResponse>,
    override val films: List<MovieResponse>,
    override val isLiked: Boolean = false,
) : IStarship