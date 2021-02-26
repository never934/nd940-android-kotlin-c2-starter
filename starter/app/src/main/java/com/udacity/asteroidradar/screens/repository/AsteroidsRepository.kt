package com.udacity.asteroidradar.screens.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.network.response.AsteroidResponse
import com.udacity.asteroidradar.utils.NativeUtils
import com.udacity.asteroidradar.utils.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidsRepository {

    val asteroidsList: MutableLiveData<List<AsteroidResponse>> = MutableLiveData(ArrayList())

    suspend fun loadAsteroids(){
        withContext(Dispatchers.IO){
            val asteroids = AppClass.nasaServerComponent.nasaServerApi.loadAsteroidsFeed(NativeUtils().getNasaDecodedKey(), null, null)
            asteroidsList.postValue(parseAsteroidsJsonResult(JSONObject(asteroids.string())))
        }
    }
}