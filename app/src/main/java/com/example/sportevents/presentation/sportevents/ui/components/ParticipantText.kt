package com.example.sportevents.presentation.sportevents.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportevents.ui.theme.Dimens
import com.example.sportevents.ui.theme.SportEventsTextStyle
import com.example.sportevents.ui.theme.textStyle

@Composable
fun ParticipantText(participantName: String) {
    Text(
        modifier = Modifier.padding(horizontal = Dimens.padding_horizontal_small),
        style = textStyle(style = SportEventsTextStyle.CommonText),
        text = participantName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}