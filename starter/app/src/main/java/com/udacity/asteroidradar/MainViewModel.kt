package com.udacity.asteroidradar

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var musicPlayer: MediaPlayer = MediaPlayer.create(AppClass.androidComponent.context, R.raw.soundtrack)

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