package com.example.starwarsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarsapp.database.dao.PeopleDao
import com.example.starwarsapp.database.dao.PlanetDao
import com.example.starwarsapp.database.dao.StarshipDao
import com.example.starwarsapp.database.entity.*

@Database(
    entities = [MovieEntity::class, PilotEntity::class, PlanetEntity::class, PeopleEntity::class,
        StarshipEntity::class], version = 1
)
abstract class StarWarsDataBase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao
    abstract fun peopleDao(): PeopleDao
    abstract fun starshipDao(): StarshipDao
}