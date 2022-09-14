package com.example.swmandroid.model.community.jobreview

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobReviewItem(
    @Json(name = "comment_count")
    val commentCount: Int,
    val createdAt: String,
    val id: Int,
    @Json(name = "pass_fail")
    val passFail: Boolean,
    @Json(name = "process_category")
    val processCategory: String,
    val techStack: String,
    val text: String,
    val title: String,
    @Json(name = "user_id")
    val userId: Int,
    val viewCount: Int,
    @Json(name = "user_email")
val userEmail : String,
)