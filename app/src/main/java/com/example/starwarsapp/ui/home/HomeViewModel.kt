package com.example.starwarsapp.ui.home

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

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()
    fun init() {
        liveData.postValue(OutputOf.Loader())
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                StarshipRepository().getStarshipsByName("TIE")
            }
            val result = response.map {
                Starship.fromIStarship(it)
            }
            liveData.postValue(OutputOf.Success(result))
        }
    }

    fun updateObject(SWObject: StarWarsObject) {
        val starshipRepository = StarshipRepository()
        val peopleRepository = PeopleRepository()
        val planetRepository = PlanetRepository()
        viewModelScope.launch {
            when (SWObject) {
                is IStarship -> {
                    starshipRepository.updateStarship(
                        Starship.fromIStarship(
                            SWObject,
                            SWObject.isLike
                        )
                    )
                }
                is IPeople -> {
                    peopleRepository.updatePeople(
                        Character.fromIPeople(
                            SWObject,
                            SWObject.isLike
                        )
                    )
                }
                is IPlanet -> {
                    planetRepository.updatePlanet(
                        Planet.fromIPlanet(
                            SWObject,
                            SWObject.isLike
                        )
                    )
                }
                else -> {
                    throw IllegalArgumentException("incorrect type ${SWObject.javaClass}")
                }
            }
        }
    }
}