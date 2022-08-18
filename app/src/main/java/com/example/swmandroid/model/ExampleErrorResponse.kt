package com.example.swmandroid.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExampleErrorResponse(
    @Json(name = "failure_message")
    val failureMessage: String,
    val status: String
)