package com.example.starwarsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapp.data.models.StarWarsObject

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<List<StarWarsObject>>()
}