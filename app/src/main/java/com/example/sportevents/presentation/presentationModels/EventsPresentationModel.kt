package com.example.sportevents.presentation.presentationModels

data class SportCategoriesPresentationModel(
    val icon: Int?,
    val sportId: String,
    val sportName: String,
    var isCategoryExpanded: Boolean,
    var eventData: List<EventsPresentationModel>
)

data class EventsPresentationModel(
    val eventId: Int,
    val timeLeftToStart: Long,
    val firstParticipant: String,
    val secondParticipant: String
)