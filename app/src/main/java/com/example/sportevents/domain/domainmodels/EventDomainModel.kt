package com.example.sportevents.domain.domainmodels

data class EventsDomainModel (
    val eventId: Int,
    val sportId: String,
    val description: String,
    val eventStartTime: Long
)