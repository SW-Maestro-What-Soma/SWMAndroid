package com.example.swmandroid.model.community.delete

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteItemInfo(
    @Json(name = "content_id")
    val contentId: Int,
    @Json(name = "user_email")
    val userEmail: String,
)