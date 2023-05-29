package com.example.sportevents.presentation.sportevents.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.sportevents.domain.usecases.GetEventsUseCase
import com.example.sportevents.presentation.presentationModels.SportCategoriesPresentationModel
import com.example.sportevents.presentation.presentationModels.mapEventsDomainModelToEventsPresentationModel
import com.example.sportevents.presentation.utils.baseviewmodel.BaseViewModelImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SportEventsViewModel(private val getEventsUseCase: GetEventsUseCase): BaseViewModelImpl() {

    data class SportEventsUiState(
        val sportEvents: List<SportCategoriesPresentationModel>,
        val favoriteList: HashMap<Int, Boolean>,
        val expandedList: HashMap<String, Boolean>,
        val isEventsLoading: Boolean,
        val errorState: Boolean
    )

    private val _uiState = MutableStateFlow(
        SportEventsUiState(
            sportEvents = emptyList(),
            favoriteList = hashMapOf(),
            expandedList = hashMapOf(),
            isEventsLoading = true,
            errorState = false
        )
    )

    override val uiState: StateFlow<SportEventsUiState> = _uiState

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isEventsLoading = true) }
            launchUseCase(
                useCase = {
                    getEventsUseCase()
                },
                success = {
                        _uiState.update { state -> state.copy(sportEvents = mapEventsDomainModelToEventsPresentationModel(it)) }
                        _uiState.update { state -> state.copy(isEventsLoading = false) }
                        _uiState.update { state -> state.copy(errorState = false) }
                    },
                error = {
                    _uiState.update { state -> state.copy(isEventsLoading = false) }
                    _uiState.update { state -> state.copy(errorState = true) }
                }
            )
        }
    }

    fun changeFavoriteState(eventId: Int) {
        val favoriteList: HashMap<Int, Boolean> = HashMap(_uiState.value.favoriteList)
        if (favoriteList.contains(eventId)) {
            val curValue = favoriteList.getValue(eventId)
            favoriteList[eventId] = !curValue
        } else favoriteList[eventId] = true
        _uiState.update { state -> state.copy(favoriteList = favoriteList) }
    }

    fun changeCategoryExpandedState(categoryId: String) {
        val categoriesList: MutableList<SportCategoriesPresentationModel> = mutableListOf()
        var category: SportCategoriesPresentationModel
        _uiState.value.sportEvents.forEach {
            category = it.copy()
            if (it.sportId == categoryId) {
                category.isCategoryExpanded = !it.isCategoryExpanded
            }
            categoriesList.add(category)
        }
        _uiState.update { state -> state.copy(sportEvents = categoriesList) }
    }
}
