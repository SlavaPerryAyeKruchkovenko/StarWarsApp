package com.example.starwarsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapp.data.interfaces.IMovie

@Entity
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") override val id: String,
    @ColumnInfo(name = "movie_name") override val name: String,
    override val director: String,
    override val producer: String
): IMovie