package com.udacity.asteroidradar.screens

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.R

class MainViewModel : ViewModel() {

    private var musicPlayer: MediaPlayer = MediaPlayer.create(
        AppClass.androidComponent.context,
        R.raw.soundtrack
    )

    init {
        musicPlayer.isLooping = true
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
}