package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.db.AppDatabase
import com.udacity.asteroidradar.di.*
import com.udacity.asteroidradar.di.module.AndroidModule
import com.udacity.asteroidradar.di.module.RoomModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AppClass: Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
       lateinit var androidComponent: AndroidComponent
       lateinit var nasaServerComponent: NasaServerComponent
       lateinit var roomComponent: RoomComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            setupRecurringWork()
        }
        androidComponent = DaggerAndroidComponent.builder()
            .androidModule(AndroidModule(this, this))
            .build()
        nasaServerComponent = DaggerNasaServerComponent.builder()
            .androidComponent(androidComponent)
            .build()
        roomComponent = DaggerRoomComponent.builder()
            .androidComponent(androidComponent)
            .roomModule(RoomModule(AppDatabase.getInstance(this)))
            .build()
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()

        val repeatingRequest
                = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest)
    }
}