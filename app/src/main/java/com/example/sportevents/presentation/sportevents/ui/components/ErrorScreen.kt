package com.example.sportevents.presentation.sportevents.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.sportevents.R
import com.example.sportevents.ui.theme.Colors
import com.example.sportevents.ui.theme.Dimens
import com.example.sportevents.ui.theme.SportEventsTextStyle
import com.example.sportevents.ui.theme.textStyle

@Composable
fun ErrorScreen() {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState, enabled = true),
        contentAlignment = Alignment.Center
       ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                tint = Colors.DefaultIconColor,
                contentDescription = stringResource(id = R.string.error_message)
            )
            Text(
                modifier = Modifier.padding(top = Dimens.spacingVertical),
                text = stringResource(id = R.string.error_message),
                style = textStyle(style = SportEventsTextStyle.ErrorText)
            )
        }
    }
}