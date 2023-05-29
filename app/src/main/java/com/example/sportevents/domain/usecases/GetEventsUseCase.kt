package com.example.sportevents.domain.usecases

import com.example.sportevents.domain.abstractions.EventsRepository

class GetEventsUseCase (private val eventsRepository: EventsRepository) {
    suspend operator fun invoke() = eventsRepository.getEvents()
}