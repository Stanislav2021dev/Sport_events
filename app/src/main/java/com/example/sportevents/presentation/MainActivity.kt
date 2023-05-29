package com.example.sportevents.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportevents.presentation.sportevents.ui.SportEventsScreen
import com.example.sportevents.ui.theme.Colors
import com.example.sportevents.ui.theme.SportEventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SportEventsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Colors.BackgroundPrimaryColor),
                        contentAlignment = Alignment.TopStart
                    ) {
                        SportEventsScreen()
                    }
                }
            }
        }
    }
}