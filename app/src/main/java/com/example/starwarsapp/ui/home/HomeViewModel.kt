package com.example.starwarsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.data.models.Starship
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
}