package com.example.swmandroid.model.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserProfile(
    @Json(name = "email") val email: String,
    @Json(name = "exp") val exp: Int,
    @Json(name = "nick_name") val nick_name: String,
    @Json(name = "tech_stack") val tech_stack: String,
    @Json(name = "tier") val tier: String,
    @Json(name = "user_role") val user_role: String,
    @Json(name = "user_token") val user_token: String,
)
