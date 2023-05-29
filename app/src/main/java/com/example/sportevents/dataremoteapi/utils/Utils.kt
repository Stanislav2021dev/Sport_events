package com.example.sportevents.dataremoteapi.utils

import retrofit2.Response

sealed class CommonResponse<T : Any>

data class SuccessResponse<T : Any>(
    var data: T) : CommonResponse<T>()

data class ErrorResponse<T : Any>(
    val error: ErrorData
) : CommonResponse<T>()

data class ErrorData(
    val error: String
)

fun <T : Any, R : Any> CommonResponse<T>.convertResponse(convert: (T) -> R): CommonResponse<R> =
    try {
        when (this) {
            is ErrorResponse -> ErrorResponse(error)
            is SuccessResponse -> SuccessResponse(convert.invoke(data))
        }
    } catch (e: Exception) {
        ErrorResponse(ErrorData(error = "Error"))
    }

suspend fun <T : Any> executeRequest(request: suspend () -> Response<T>): CommonResponse<T>  =
    try {
        val response = request.invoke()
        when (response.isSuccessful) {
            true -> SuccessResponse(response.body() as T)
            else -> ErrorResponse(ErrorData(error = "Error"))
        }
    } catch (e: Exception) {
        ErrorResponse(ErrorData(error = "Error"))
    }