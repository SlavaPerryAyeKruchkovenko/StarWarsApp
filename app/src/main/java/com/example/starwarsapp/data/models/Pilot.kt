package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IPilot

data class Pilot(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
) : IPilot