package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IPilot

@Entity
data class PilotEntity(
    @PrimaryKey
    @ColumnInfo(name = "pilot_id") val id: String,
    @ColumnInfo(name = "pilot_name") val name: String,
    val sex: String,
    val starshipCount: Int
)