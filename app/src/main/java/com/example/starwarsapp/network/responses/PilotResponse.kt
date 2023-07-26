package com.example.starwarsapp.network.responses

import com.example.starwarsapp.data.interfaces.IPilot

data class PilotResponse(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
) : IPilot {
    companion object {
        fun fromIPilot(pilot: IPilot): PilotResponse {
            return PilotResponse(pilot.id, pilot.name, pilot.sex, pilot.starshipCount)
        }
    }
}