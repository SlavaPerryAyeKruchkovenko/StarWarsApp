package com.example.starwarsapp.data.responses

import com.example.starwarsapp.data.interfaces.IPilot

data class PilotResponse(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
):IPilot