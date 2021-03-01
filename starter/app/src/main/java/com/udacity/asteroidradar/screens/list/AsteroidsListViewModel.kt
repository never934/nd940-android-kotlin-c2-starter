package com.udacity.asteroidradar.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import com.udacity.asteroidradar.screens.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class AsteroidsListViewModel : ViewModel() {

    private var repository = AsteroidsRepository()
    val asteroids: LiveData<List<AsteroidDB>> = repository.asteroids
    val imageOfDay: LiveData<ImageOfDayResponse> = repository.imageOfDay

    init{
        loadAsteroids()
        loadImageOfDay()
    }

    private fun loadAsteroids(){
        viewModelScope.launch {
            repository.loadAsteroids()
        }
    }

    private fun loadImageOfDay(){
        viewModelScope.launch {
            repository.loadImageOfDay()
        }
    }
}