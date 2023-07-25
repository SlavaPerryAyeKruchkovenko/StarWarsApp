package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.interfaces.IPilot

data class Pilot(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
) : IPilot {
    companion object {
        fun fromIPilot(pilot: IPilot): Pilot {
            return Pilot(pilot.id, pilot.name, pilot.sex, pilot.starshipCount)
        }
    }
}