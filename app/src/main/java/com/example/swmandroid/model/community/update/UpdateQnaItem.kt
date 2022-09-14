package com.example.swmandroid.model.community.update

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateQnaItem(
    val id: Int,
    @Json(name = "tech_stack")
    val techStack: String,
    val text: String,
    val title: String,
    @Json(name = "user_email")
    val userEmail: String,
)