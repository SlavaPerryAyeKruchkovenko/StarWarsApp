package com.example.starwarsapp.data.responses

import com.example.starwarsapp.data.interfaces.IStarship

class StarshipImpResponse(
    override val id: String,
    override val name: String,
    override val model: String,
    override val manufacturer: String,
    override val pilots: List<PilotResponse>,
    override val films: List<MovieResponse>
) : IStarship