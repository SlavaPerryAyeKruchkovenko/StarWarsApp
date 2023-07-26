package com.example.starwarsapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.data.models.*
import com.example.starwarsapp.data.utils.RequestType
import com.example.starwarsapp.repository.people.PeopleRepository
import com.example.starwarsapp.repository.planet.PlanetRepository
import com.example.starwarsapp.repository.starship.StarshipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()
    private var lastName: String? = null
    private var lastType: RequestType? = null
    private val starshipRepository = StarshipRepository()
    private val peopleRepository = PeopleRepository()
    private val planetRepository = PlanetRepository()
    fun init() {
        liveData.postValue(OutputOf.Nothing())
    }

    fun findByName(name: String, type: RequestType) {
        Log.e("lastName",lastName.toString())
        Log.e("name",name)
        Log.e("res",(lastName != name || lastType != type).toString())
        if (name.trim().length >= 2) {
            if (liveData.value !is OutputOf.Loader && (lastName != name || lastType != type)) {
                liveData.postValue(OutputOf.Loader())
                lastName = name
                lastType = type
                viewModelScope.launch {
                    val result = when (type) {
                        RequestType.ALL -> {
                            getPlanets(name) + getCharacters(name) + getStarships(name)
                        }
                        RequestType.PLANETS -> {
                            getPlanets(name)
                        }
                        RequestType.CHARACTERS -> {
                            getCharacters(name)
                        }
                        RequestType.STARSHIPS -> {
                            getStarships(name)
                        }
                    }
                    liveData.postValue(
                        if (result.isNotEmpty()) OutputOf.Success(result)
                        else OutputOf.Error("not found")
                    )
                }
            }
        } else {
            liveData.postValue(OutputOf.Nothing())
        }
    }

    fun updateObject(SWObject: StarWarsObject) {

        viewModelScope.launch {
            when (SWObject) {
                is IStarship -> {
                    starshipRepository.updateStarship(
                        Starship.fromIStarship(SWObject, SWObject.isLike)
                    )
                }
                is IPeople -> {
                    peopleRepository.updatePeople(
                        Character.fromIPeople(SWObject, SWObject.isLike)
                    )
                }
                is IPlanet -> {
                    planetRepository.updatePlanet(
                        Planet.fromIPlanet(SWObject, SWObject.isLike)
                    )
                }
                else -> {
                    throw IllegalArgumentException("incorrect type ${SWObject.javaClass}")
                }
            }
        }
    }

    private suspend fun getPlanets(name: String): List<StarWarsObject> {
        val response = withContext(Dispatchers.IO) {
            planetRepository.getPlanetsByName(name)
        }
        return response.map {
            Planet.fromIPlanet(it, it.isLiked)
        }
    }

    private suspend fun getCharacters(name: String): List<StarWarsObject> {
        val response = withContext(Dispatchers.IO) {
            peopleRepository.getPeoplesByName(name)
        }
        return response.map {
            Character.fromIPeople(it, it.isLiked)
        }
    }

    private suspend fun getStarships(name: String): List<StarWarsObject> {
        val response = withContext(Dispatchers.IO) {
            starshipRepository.getStarshipsByName(name)
        }
        return response.map {
            Starship.fromIStarship(it, it.isLiked)
        }
    }
}