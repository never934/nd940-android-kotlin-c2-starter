package com.udacity.asteroidradar.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.network.response.AsteroidResponse
import com.udacity.asteroidradar.screens.repository.AsteroidsRepository
import com.udacity.asteroidradar.utils.NativeUtils
import kotlinx.coroutines.launch

class AsteroidsListViewModel : ViewModel() {

    private var repository = AsteroidsRepository()
    val asteroids: LiveData<List<AsteroidResponse>> = repository.asteroidsList

    init{
        getAsteroids()
    }

    private fun getAsteroids(){
        viewModelScope.launch {
            repository.loadAsteroids()
        }
    }
}