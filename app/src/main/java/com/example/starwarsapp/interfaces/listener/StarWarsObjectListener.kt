package com.example.starwarsapp.interfaces.listener

import com.example.starwarsapp.data.models.StarWarsObject

interface StarWarsObjectListener {
    fun onClick(SWObject: StarWarsObject)
}