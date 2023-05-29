package com.example.sportevents.presentation.utils.baseviewmodel

import com.example.sportevents.dataremoteapi.utils.CommonResponse
import com.example.sportevents.dataremoteapi.utils.ErrorData
import kotlinx.coroutines.flow.StateFlow

interface BaseViewModel {
    val uiState: StateFlow<Any>

    suspend fun <T : Any> launchUseCase(
        useCase: suspend () -> CommonResponse<T>,
        success: suspend (data: T) -> Unit,
        error: suspend (error: ErrorData) -> Unit,
    )
}