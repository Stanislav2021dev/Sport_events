package com.example.sportevents.presentation.presentationModels

import com.example.sportevents.R
import com.example.sportevents.domain.domainmodels.SportCategoriesDomainModel

fun mapEventsDomainModelToEventsPresentationModel(events: List<SportCategoriesDomainModel>): List<SportCategoriesPresentationModel> =
    events.map { event ->
        SportCategoriesPresentationModel(
            icon = getSportIcon(event.sportId),
            sportId = event.sportId,
            sportName = event.sportName,
            isCategoryExpanded = true,
            eventData = event.eventData.map { eventData ->
                EventsPresentationModel(
                    eventId = eventData.eventId,
                    firstParticipant = eventData.description.mapToParticipants()[0].trim(),
                    secondParticipant = try {
                        eventData.description.mapToParticipants()[1].trim()
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        ""
                    },
                    timeLeftToStart = getTimeLeftInMillis(eventData.eventStartTime)
                )
            }
        )
    }


fun getSportIcon(sportId: String): Int? =
    when (sportId) {
        "FOOT" -> R.drawable.ic_soccer
        "BASK" -> R.drawable.ic_bascetball
        "TENN" -> R.drawable.ic_tennis
        "TABL" -> R.drawable.ic_tabble_tennis
        "HAND" -> R.drawable.ic_handball
        "BCHV" -> R.drawable.ic_beach_voleyball
        "ESPS" -> R.drawable.ic_e_sports
        else -> null
    }

private fun String.mapToParticipants(): Array<String> = this.split("-").toTypedArray()

private fun getTimeLeftInMillis(startTime: Long): Long =
    (startTime - (System.currentTimeMillis()/1000)) * 1000