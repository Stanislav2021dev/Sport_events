package com.example.sportevents.dataremoteapi.api

import com.example.sportevents.dataremoteapi.data.SportCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface SportEventsApi {
    @GET("demo")
    suspend fun getSportEvents(): Response<List<SportCategoriesResponse>>
}