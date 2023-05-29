package com.example.sportevents.domain.domainmodels

data class SportCategoriesDomainModel(
    val sportId: String,
    val sportName: String,
    val eventData: List<EventsDomainModel>
)