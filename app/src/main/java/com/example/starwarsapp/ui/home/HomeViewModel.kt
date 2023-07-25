package com.example.starwarsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.repository.Mock

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<List<StarWarsObject>>()
    fun init() {
        liveData.postValue(Mock().getStarWarsObjects())
    }
}