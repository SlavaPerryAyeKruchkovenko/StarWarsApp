package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IPilot
import com.example.starwarsapp.data.models.Pilot

@Entity
data class PilotEntity(
    @PrimaryKey
    @ColumnInfo(name = "pilot_id") val id: String,
    @ColumnInfo(name = "pilot_name") val name: String,
    val sex: String,
    val starshipCount: Int
) {
    companion object {
        fun fromIPilot(pilot: IPilot): PilotEntity {
            return PilotEntity(pilot.id, pilot.name, pilot.sex, pilot.starshipCount)
        }
    }
}