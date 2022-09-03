package com.example.swmandroid.model.community.jobreview

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobReviewItem(
    @Json(name = "comment_count")
    val commentCount: Int,
    @Json(name = "created_at")
    val createdAt: String,
    val id: Int,
    @Json(name = "pass_fail")
    val passFail: Boolean,
    @Json(name = "process_category")
    val processCategory: String,
    @Json(name = "techCategory")
    val techCategory: String,
    val text: String,
    val title: String,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "view_count")
    val viewCount: Int
)