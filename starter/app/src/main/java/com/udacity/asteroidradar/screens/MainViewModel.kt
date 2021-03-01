package com.udacity.asteroidradar.screens

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.utils.SharedPreferencesUtils

class MainViewModel : ViewModel() {

    private var musicPlayer: MediaPlayer = MediaPlayer.create(
        AppClass.androidComponent.context,
        R.raw.soundtrack
    )

    init {
        musicPlayer.isLooping = true
        if(SharedPreferencesUtils().getVolumeEnabledState().not()){
            musicPlayer.setVolume(0.toFloat(),0.toFloat())
        }
    }

    fun resumeMusic(){
        musicPlayer.start()
    }

    fun pauseMusic(){
        musicPlayer.pause()
    }

    override fun onCleared() {
        super.onCleared()
        musicPlayer.release()
    }

    fun unmuteMusic() {
        SharedPreferencesUtils().saveVolumeEnabledState(true)
        musicPlayer.setVolume(1.toFloat(),1.toFloat())
    }

    fun muteMusic() {
        SharedPreferencesUtils().saveVolumeEnabledState(false)
        musicPlayer.setVolume(0.toFloat(),0.toFloat())
    }
}