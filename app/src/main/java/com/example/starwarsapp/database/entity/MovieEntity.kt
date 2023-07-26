package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val id: String,
    @ColumnInfo(name = "movie_name") val name: String,
    val director: String,
    val producer: String
)