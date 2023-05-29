package com.example.sportevents.presentation.sportevents.ui.components

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sportevents.ui.theme.Colors
import com.example.sportevents.ui.theme.Dimens
import com.example.sportevents.ui.theme.SportEventsTextStyle
import com.example.sportevents.ui.theme.textStyle
import kotlinx.coroutines.delay
import com.example.sportevents.R

@Composable
fun Timer(initialValue: Long) {
    val context = LocalContext.current
    var timerValue = initialValue
    var timerState by remember { mutableStateOf(getTimeLeft(timerValue, context)) }
    var isTimerActive by remember { mutableStateOf(initialValue > 0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .border(
                Dimens.borderWidth,
                Colors.PrimaryTextColor,
                shape = RoundedCornerShape(Dimens.smallShapes),
            ),
        contentAlignment = Alignment.Center

    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = timerState,
            style = textStyle(SportEventsTextStyle.CommonText)
        )
    }

    LaunchedEffect(isTimerActive) {
            if(isTimerActive){
                while (initialValue > 0) {
                        delay(1000)
                        timerValue -= 1000
                        timerState = getTimeLeft(timerValue, context)
                }
                isTimerActive = false
            }
            else timerState = context.getString(R.string.event_started)
    }
}

private fun getTimeLeft(secondsToStart: Long, context: Context): String {
    val format = "%1$02d"
    var diff = secondsToStart

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val daysLeft = diff / daysInMilli
    diff %= daysInMilli.toInt()

    val hoursLeft = diff / hoursInMilli
    diff %= hoursInMilli.toInt()

    val minutesLeft = diff / minutesInMilli
    diff %= minutesInMilli.toInt()

    val secondsLeft = diff / secondsInMilli

    return context.getString(R.string.time_left).format(
        daysLeft.toString(),
        hoursLeft.toString(),
        minutesLeft.toString(),
        String.format(format, secondsLeft)
    )
}