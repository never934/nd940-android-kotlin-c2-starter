package com.udacity.asteroidradar.screens.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.R


class InfoViewModel : ViewModel() {

    val backgroundUrl: String = AppClass.androidComponent.resources.getString(R.string.background_link)
    val musicUrl: String = AppClass.androidComponent.resources.getString(R.string.music_link)

    private val _openBackgroundUrlEvent: MutableLiveData<Boolean> = MutableLiveData(false)
    val openBackgroundUrlEvent: LiveData<Boolean>
    get() = _openBackgroundUrlEvent

    private val _openMusicUrlEvent: MutableLiveData<Boolean> = MutableLiveData(false)
    val openMusicUrlEvent: LiveData<Boolean>
        get() = _openMusicUrlEvent

    fun musicUrlOpened(){
        _openMusicUrlEvent.value = false
    }

    fun backgroundUrlOpened(){
        _openBackgroundUrlEvent.value = false
    }

    fun openMusicUrl(){
        _openMusicUrlEvent.value = true
    }

    fun openBackgroundUrl(){
        _openBackgroundUrlEvent.value = true
    }
}