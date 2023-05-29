package com.example.sportevents.dataremoteapi.remotemanagers

import com.example.sportevents.dataremoteapi.RetrofitInitializer
import com.example.sportevents.dataremoteapi.api.SportEventsApi
import com.example.sportevents.dataremoteapi.utils.CommonResponse
import com.example.sportevents.dataremoteapi.utils.convertResponse
import com.example.sportevents.dataremoteapi.utils.executeRequest
import com.example.sportevents.domain.abstractions.EventsRepository
import com.example.sportevents.domain.domainmodels.EventsDomainModel
import com.example.sportevents.domain.domainmodels.SportCategoriesDomainModel

class EventsRemoteManager: EventsRepository {
    private val api: SportEventsApi by lazy { RetrofitInitializer.initApi(RetrofitInitializer.getOkHttpBuilder().build()) }

    override suspend fun getEvents(): CommonResponse<List<SportCategoriesDomainModel>> {
        executeRequest {
            api.getSportEvents()
        }.also { response ->
            return response.convertResponse { events ->
                events.map {
                    SportCategoriesDomainModel(
                        sportId = it.sportId,
                        sportName = it.sportName,
                        eventData = it.eventData.map { eventData ->
                            EventsDomainModel(
                                eventId = eventData.eventId,
                                eventStartTime = eventData.eventStartTime,
                                description = eventData.description,
                                sportId = eventData.sportId
                            )
                        }
                    )
                }
            }
        }
    }
}