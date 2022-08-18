package com.example.swmandroid.model.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginInfo(
    @Json(name = "email") val email: String,
    @Json(name = "user_pw") val user_pw: String,
)
