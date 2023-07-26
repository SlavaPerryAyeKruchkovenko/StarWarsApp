package com.example.starwarsapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.starwarsapp.data.interfaces.IPeople

@Entity
data class PeopleEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val sex: String,
    val starshipsCount: Int,
    var isLike: Int,
)