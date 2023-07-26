package com.example.starwarsapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.data.models.Starship
import com.example.starwarsapp.repository.Mock
import com.example.starwarsapp.repository.starship.StarshipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()

    fun init() {
        liveData.postValue(OutputOf.Loader())
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                StarshipRepository().getLikedStarships()
            }
            val result = response.map {
                Starship.fromIStarship(it)
            }
            liveData.postValue(OutputOf.Success(result))
        }
    }

    fun removeElement(starWarsObject: StarWarsObject) {
        if (liveData.value is OutputOf.Success) {
            val success = liveData.value as OutputOf.Success
            val data = success.value.filter {
                it.javaClass !== starWarsObject.javaClass || it !== starWarsObject
            }
            liveData.postValue(OutputOf.Success(data))
        }
    }

    fun clear() {
        liveData.postValue(OutputOf.Nothing())
    }
}