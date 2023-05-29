package com.example.sportevents.presentation.sportevents.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sportevents.R
import com.example.sportevents.presentation.presentationModels.EventsPresentationModel
import com.example.sportevents.ui.theme.Colors
import com.example.sportevents.ui.theme.Dimens

@Composable
fun EventItem(
    event: EventsPresentationModel,
    favoriteEvents: Map<Int, Boolean>?,
    changeFavoriteState: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(Dimens.eventItemWidth)
            .padding(vertical = Dimens.spacingVertical),
        horizontalAlignment = CenterHorizontally
    ) {
        Timer(initialValue = event.timeLeftToStart)

        Icon(
            modifier = Modifier
                .padding(vertical = Dimens.padding_small)
                .clickable { changeFavoriteState(event.eventId) },
            tint = Colors.DefaultIconColor,
            painter = painterResource(id = getIcon(favoriteEvents, event.eventId)),
            contentDescription = stringResource(id = R.string.favorite_event_content_description)
        )
        ParticipantText(participantName = event.firstParticipant)
        ParticipantText(participantName = event.secondParticipant)
    }
}

private fun getIcon(favoriteEvents: Map<Int, Boolean>?, eventId: Int): Int =
    if (favoriteEvents?.contains(eventId) == true) {
        if (favoriteEvents[eventId] == true) {
            R.drawable.ic_favorite_selected
        } else R.drawable.ic_favorite_unselected
    } else R.drawable.ic_favorite_unselected

@Composable
@Preview
fun EventItemPreview() {
    EventItem(
        event = EventsPresentationModel(
            eventId = 0,
            timeLeftToStart = 100000,
            firstParticipant = "Manchester United",
            secondParticipant = "Liverpool"
        ),
        changeFavoriteState = {},
        favoriteEvents = emptyMap()
    )
}