package com.example.swmandroid.model.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmailConfirm(
    val certification: String,
)