package com.example.starwarsapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.repository.Mock

class FavouriteViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()

    fun init() {
        liveData.postValue(OutputOf.Success(Mock().getStarWarsObjects()))
    }

    fun removeElement(starWarsObject: StarWarsObject) {
        if (liveData.value is OutputOf.Success) {
            val success = liveData.value as OutputOf.Success
            val data = success.value.filter {
                it.javaClass !== starWarsObject.javaClass || it._id !== starWarsObject._id
            }
            liveData.postValue(OutputOf.Success(data))
        }
    }

    fun clear() {
        liveData.postValue(OutputOf.Nothing())
    }
}