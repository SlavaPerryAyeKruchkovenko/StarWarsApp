package com.example.starwarsapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.repository.Mock

class FavouriteViewModel : ViewModel() {
    val liveData = MutableLiveData<List<StarWarsObject>>()

    fun init() {
        liveData.postValue(Mock().getStarWarsObjects())
    }

    fun removeElement(starWarsObject: StarWarsObject) {
        if (liveData.value != null) {
            liveData.postValue(liveData.value!!.filter {
                it.javaClass !== starWarsObject.javaClass || it.id !== starWarsObject.id
            })
        }
    }

    fun clear() {
        liveData.postValue(listOf())
    }
}