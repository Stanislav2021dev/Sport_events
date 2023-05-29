package com.example.sportevents.dataremoteapi.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SportCategoriesResponse(
    @JsonProperty("i")
    val sportId: String,
    @JsonProperty("d")
    val sportName: String,
    @JsonProperty("e")
    val eventData: List<EventsResponse>
)