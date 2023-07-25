package com.example.starwarsapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.models.Character
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.repository.people.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<OutputOf<List<StarWarsObject>>>()
    private var copyLiveData: List<StarWarsObject> = listOf()
    fun init() {
        liveData.postValue(OutputOf.Loader())
        viewModelScope.launch {
            val peoples = withContext(Dispatchers.IO) {
                PeopleRepository().getPeoplesByName("w")
            }
            copyLiveData = peoples.map {
                Character.fromICharacter(it)
            }
            liveData.postValue(OutputOf.Success(copyLiveData))
        }
    }
}