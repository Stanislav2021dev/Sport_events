package com.example.sportevents.domain.abstractions

import com.example.sportevents.dataremoteapi.utils.CommonResponse
import com.example.sportevents.domain.domainmodels.SportCategoriesDomainModel

interface EventsRepository {
    suspend fun getEvents(): CommonResponse<List<SportCategoriesDomainModel>>
}