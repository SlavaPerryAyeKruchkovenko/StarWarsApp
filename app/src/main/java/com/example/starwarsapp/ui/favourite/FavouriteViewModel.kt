package com.example.starwarsapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.interfaces.IPeople
import com.example.starwarsapp.data.interfaces.IPlanet
import com.example.starwarsapp.data.interfaces.IStarship
import com.example.starwarsapp.data.models.*
import com.example.starwarsapp.repository.people.PeopleRepository
import com.example.starwarsapp.repository.planet.PlanetRepository
import com.example.starwarsapp.repository.starship.StarshipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()
    private val starshipRepository = StarshipRepository()
    private val peopleRepository = PeopleRepository()
    private val planetRepository = PlanetRepository()
    fun init() {
        liveData.postValue(OutputOf.Loader())
        viewModelScope.launch {
            val result = getAllLiked()
            liveData.postValue(
                if (result.isNotEmpty()) OutputOf.Success(result)
                else OutputOf.Nothing()
            )
        }
    }

    fun dislikeElement(SWObject: StarWarsObject) {
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
            val result = getAllLiked()
            liveData.postValue(
                if (result.isNotEmpty()) OutputOf.Success(result)
                else OutputOf.Nothing()
            )
        }
    }

    fun clear() {
        viewModelScope.launch {
            starshipRepository.dislikeAllStarships()
            peopleRepository.dislikeAllPeoples()
            planetRepository.dislikeAllPlanets()
            val result = getAllLiked()
            liveData.postValue(
                if (result.isNotEmpty()) OutputOf.Success(result)
                else OutputOf.Nothing()
            )
        }
    }

    private suspend fun getAllLiked(): List<StarWarsObject> {
        val starships = withContext(Dispatchers.IO) {
            starshipRepository.getLikedStarships()
        }
        val peoples = withContext(Dispatchers.IO) {
            peopleRepository.getLikedPeoples()
        }
        val planets = withContext(Dispatchers.IO) {
            planetRepository.getLikedPlanets()
        }
        val starshipsRes = starships.map {
            Starship.fromIStarship(it, it.isLiked)
        }
        val peoplesRes = peoples.map {
            Character.fromIPeople(it, it.isLiked)
        }
        val planetsRes = planets.map {
            Planet.fromIPlanet(it, it.isLiked)
        }
        return starshipsRes + peoplesRes + planetsRes
    }
}