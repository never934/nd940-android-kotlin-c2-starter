package com.udacity.asteroidradar.screens.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.enums.ResponseStatus
import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import com.udacity.asteroidradar.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class AsteroidsRepository {

    val asteroids: LiveData<List<AsteroidDB>> = AppClass.roomComponent.asteroidsDao.getAsteroids()

    private val _imageOfDay: MutableLiveData<ImageOfDayResponse> = MutableLiveData()
    val imageOfDay: LiveData<ImageOfDayResponse>
    get() = _imageOfDay

    private val _responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()
    val responseStatus: LiveData<ResponseStatus>
    get() = _responseStatus

    suspend fun loadAsteroids(){
        withContext(Dispatchers.IO){
            try {
                _responseStatus.postValue(ResponseStatus.loading)
                val asteroidsResponse = AppClass.nasaServerComponent.nasaServerApi.loadAsteroidsFeed(NativeUtils().getNasaDecodedKey(), null, null)
                val asteroidsList = parseAsteroidsJsonResult(JSONObject(asteroidsResponse.string()))
                AppClass.roomComponent.asteroidsDao.insertAll(AsteroidResponseContainer(asteroidsList).asDatabaseModel())
                _responseStatus.postValue(ResponseStatus.done)
            }catch (e: Exception){
                _responseStatus.postValue(ResponseStatus.error)
            }
        }
    }

    suspend fun loadImageOfDay(){
        withContext(Dispatchers.IO){
            try {
                _imageOfDay.postValue(AppClass.nasaServerComponent.nasaServerApi.loadImageOfADay(NativeUtils().getNasaDecodedKey()))
            }catch (e: Exception){

            }
        }
    }

    suspend fun deleteYesterdayAsteroids(){
        withContext(Dispatchers.IO){
            val asteroids = AppClass.roomComponent.asteroidsDao.getAsteroids().value
            asteroids?.let {
                AppClass.roomComponent.asteroidsDao.deleteAsteroids(
                        asteroids.filter {
                            it.closeApproachDate == CalendarUtils.getYesterdayFormatted()
                        }
                )
            }
        }
    }
}