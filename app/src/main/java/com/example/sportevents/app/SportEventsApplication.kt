package com.example.sportevents.app

import android.app.Application
import com.example.sportevents.dataremoteapi.di.DataRemoteApiDiModule
import com.example.sportevents.presentation.presentationdi.PresentationDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SportEventsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            androidLogger()
            androidContext(this@SportEventsApplication)
            modules(
                modules = DataRemoteApiDiModule.koinModule + PresentationDiModule.koinModule
            )
        }
    }
}