package com.example.starwarsapp.database.models

import com.example.starwarsapp.data.interfaces.IPilot
import com.example.starwarsapp.database.entity.MovieEntity
import com.example.starwarsapp.database.entity.PilotEntity

data class PilotDB(
    override val id: String,
    override val name: String,
    override val sex: String,
    override val starshipCount: Int
) : IPilot {
    companion object {
        fun fromPilotEntity(pilot: PilotEntity): PilotDB {
            return PilotDB(
                pilot.id,
                pilot.name,
                pilot.sex,
                pilot.starshipCount,
            )
        }
    }
}