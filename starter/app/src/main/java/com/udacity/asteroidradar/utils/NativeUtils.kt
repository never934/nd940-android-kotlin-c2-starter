package com.udacity.asteroidradar.utils
import android.util.Base64

class NativeUtils {
    init {
        System.loadLibrary("keys")
    }
    private external fun getNasaKey(): String?

    fun getNasaDecodedKey() : String {
        return String(Base64.decode(getNasaKey(), Base64.DEFAULT))
    }
}