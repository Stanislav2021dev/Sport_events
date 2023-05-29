package com.example.sportevents.dataremoteapi.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventsResponse (
    @JsonProperty("i")
    val eventId: Int,
    @JsonProperty("si")
    val sportId: String,
    @JsonProperty("d")
    val description: String,
    @JsonProperty("tt")
    val eventStartTime: Long
)