package com.example.sportevents.presentation.utils.baseviewmodel

import androidx.lifecycle.ViewModel
import com.example.sportevents.dataremoteapi.utils.CommonResponse
import com.example.sportevents.dataremoteapi.utils.ErrorData
import com.example.sportevents.dataremoteapi.utils.ErrorResponse
import com.example.sportevents.dataremoteapi.utils.SuccessResponse
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModelImpl: ViewModel(), BaseViewModel {

    abstract override val uiState: StateFlow<Any>

    override suspend fun <T : Any> launchUseCase(
        useCase: suspend () -> CommonResponse<T>,
        success: suspend (data: T) -> Unit,
        error: suspend (error: ErrorData) -> Unit,
    ) {
        when (val response = useCase()) {
            is SuccessResponse -> {
                success(response.data)
            }
            is ErrorResponse -> {
                error(response.error)
            }
        }
    }
}