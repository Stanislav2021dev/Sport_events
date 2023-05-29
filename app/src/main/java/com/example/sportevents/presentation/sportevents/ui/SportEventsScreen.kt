package com.example.sportevents.presentation.sportevents.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.sportevents.presentation.sportevents.ui.components.ErrorScreen
import com.example.sportevents.presentation.sportevents.ui.components.EventItem
import com.example.sportevents.presentation.sportevents.ui.components.Header
import com.example.sportevents.presentation.sportevents.viewmodel.SportEventsViewModel
import com.example.sportevents.ui.theme.Dimens
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel

data class SportEventsActions(
    val getEvents: () -> Unit,
    val changeCategoryExpandedState: (String) -> Unit,
    val changeEventFavoriteState: (Int) -> Unit
)

@Composable
fun SportEventsScreen() {
    val viewModel: SportEventsViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsState(
        initial = SportEventsViewModel.SportEventsUiState(
            expandedList = hashMapOf(),
            favoriteList = hashMapOf(),
            sportEvents = mutableListOf(),
            errorState = false,
            isEventsLoading = false
        )
    )

    val actions = SportEventsActions(
        getEvents = { viewModel.getEvents() },
        changeCategoryExpandedState = { categoryId: String -> viewModel.changeCategoryExpandedState(categoryId) },
        changeEventFavoriteState = { eventId: Int -> viewModel.changeFavoriteState(eventId) }
    )

    SportEventsContent(
        uiState = uiState,
        actions = actions
    )
}

@Composable
fun SportEventsContent(uiState: SportEventsViewModel.SportEventsUiState,
                       actions: SportEventsActions
) {
    val swipeRefreshState = rememberSwipeRefreshState(uiState.isEventsLoading)
    SwipeRefresh(
                 state = swipeRefreshState,
                 swipeEnabled = true,
                 onRefresh = { actions.getEvents() }) {
        when (uiState.errorState) {
            true -> ErrorScreen()
            false ->  MainContent(uiState = uiState, actions = actions)
        }
    }
}


@Composable
fun MainContent(uiState: SportEventsViewModel.SportEventsUiState,
                actions: SportEventsActions) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = Dimens.spacingVertical
        ),
    ) {
        items(uiState.sportEvents) {events ->
            Header(
                event = events,
                expandAction = { categoryId ->
                    actions.changeCategoryExpandedState(categoryId)
                }
            )
            AnimatedVisibility(visible = events.isCategoryExpanded) {
                LazyRow(modifier = Modifier) {
                    items(events.eventData) { event ->
                        EventItem(
                            event = event,
                            changeFavoriteState = { eventId -> actions.changeEventFavoriteState(eventId) },
                            favoriteEvents = uiState.favoriteList
                        )
                    }
                }
            }
        }
    }
}