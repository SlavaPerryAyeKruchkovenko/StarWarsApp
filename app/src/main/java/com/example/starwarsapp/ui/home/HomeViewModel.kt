package com.example.starwarsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.Planet
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.repository.planet.PlanetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()
    fun init() {
        liveData.postValue(OutputOf.Loader())
        viewModelScope.launch {
            val peoples = withContext(Dispatchers.IO) {
                PlanetRepository().getPlanetsByName("jon")
            }
            val result = peoples.map {
                Planet.fromIPlanet(it)
            }
            liveData.postValue(OutputOf.Success(result))
        }
    }
}