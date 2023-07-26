package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IPilot

data class PilotDB(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
) : IPilot