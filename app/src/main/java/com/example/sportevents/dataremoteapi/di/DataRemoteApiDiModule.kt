package com.example.sportevents.dataremoteapi.di

import com.example.sportevents.dataremoteapi.remotemanagers.EventsRemoteManager
import com.example.sportevents.domain.abstractions.EventsRepository
import org.koin.dsl.module

object DataRemoteApiDiModule {
    val koinModule = module {
        factory<EventsRepository> {
            EventsRemoteManager()
        }
    }
}