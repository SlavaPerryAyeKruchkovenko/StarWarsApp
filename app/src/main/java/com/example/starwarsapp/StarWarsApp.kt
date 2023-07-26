package com.example.starwarsapp

import android.app.Application
import androidx.room.Room
import com.example.starwarsapp.database.StarWarsDataBase

class StarWarsApp: Application() {
    companion object {
        private var database: StarWarsDataBase? = null
        fun getDatabase(): StarWarsDataBase? {
            return database
        }

    }
    override fun onCreate() {
        super.onCreate()
        database= Room.databaseBuilder(this, StarWarsDataBase::class.java, "StarWarsDataBase").build()
    }
}