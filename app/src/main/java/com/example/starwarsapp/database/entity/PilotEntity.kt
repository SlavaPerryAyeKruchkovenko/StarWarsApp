package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IMovie
import com.example.starwarsapp.data.interfaces.IPilot

data class PilotEntity(
    @PrimaryKey
    @ColumnInfo(name = "pilot_id") override val id: String,
    @ColumnInfo(name = "pilot_name") override val name: String,
    override val sex: String,
    override val starshipCount: Int
): IPilot