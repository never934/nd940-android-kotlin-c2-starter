package com.udacity.asteroidradar.screens.list

import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import com.udacity.asteroidradar.screens.repository.AsteroidsRepository
import com.udacity.asteroidradar.utils.CalendarUtils
import com.udacity.asteroidradar.utils.NativeUtils
import kotlinx.coroutines.launch

class AsteroidsListViewModel : ViewModel() {

    private var repository = AsteroidsRepository()
    val asteroids: LiveData<List<AsteroidDB>>
    get() = Transformations.switchMap(chosenSortButton){
        Log.e("map", "transform $it")
        when(it){
            R.id.show_week -> repository.asteroids.map { list: List<AsteroidDB> ->
                list.filter { asteroidDB ->
                    CalendarUtils.getNextSevenDaysFormattedDates().contains(asteroidDB.closeApproachDate)
                }
            }
            R.id.show_today -> repository.asteroids.map { list: List<AsteroidDB> ->
                list.filter { asteroidDB ->
                    asteroidDB.closeApproachDate == CalendarUtils.getTonightFormatted()
                }
            }
            else -> repository.asteroids
        }
    }

    val imageOfDay: LiveData<ImageOfDayResponse> = repository.imageOfDay
    private val chosenSortButton: MutableLiveData<Int> = MutableLiveData(R.id.show_saved)


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

    fun filter(button: Int){
        when(button){
            R.id.show_saved -> chosenSortButton.value = R.id.show_saved
            R.id.show_today -> chosenSortButton.value = R.id.show_today
            R.id.show_week -> chosenSortButton.value = R.id.show_week
        }
    }
}