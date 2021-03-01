package com.udacity.asteroidradar.screens.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.db.AsteroidsDao
import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import com.udacity.asteroidradar.utils.AsteroidResponseContainer
import com.udacity.asteroidradar.utils.NativeUtils
import com.udacity.asteroidradar.utils.asDatabaseModel
import com.udacity.asteroidradar.utils.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class AsteroidsRepository {

    val asteroids: LiveData<List<AsteroidDB>> = AppClass.roomComponent.asteroidsDao.getAsteroids()
    val imageOfDay: MutableLiveData<ImageOfDayResponse> = MutableLiveData()

    suspend fun loadAsteroids(){
        withContext(Dispatchers.IO){
            try {
                val asteroidsResponse = AppClass.nasaServerComponent.nasaServerApi.loadAsteroidsFeed(NativeUtils().getNasaDecodedKey(), null, null)
                val asteroidsList = parseAsteroidsJsonResult(JSONObject(asteroidsResponse.string()))
                AppClass.roomComponent.asteroidsDao.insertAll(AsteroidResponseContainer(asteroidsList).asDatabaseModel())
            }catch (e: Exception){
                Log.e("internet", "lost connection")
            }
        }
    }

    suspend fun loadImageOfDay(){
        withContext(Dispatchers.IO){
            try {
                imageOfDay.postValue(AppClass.nasaServerComponent.nasaServerApi.loadImageOfADay(NativeUtils().getNasaDecodedKey()))
            }catch (e: Exception){
                Log.e("internet", "lost connection")
            }
        }
    }
}