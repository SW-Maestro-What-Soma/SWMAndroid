package com.example.swmandroid.model.community.jobposting


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobPostingItem(
    val career: String,
    @Json(name = "created_at")
    val createdAt: String,
    val id: Int,
    @Json(name = "incruit_link")
    val incruitLink: String,
    @Json(name = "start_end_time")
    val startEndTime: String,
    @Json(name = "techCategory")
    val techCategory: String,
    val text: String,
    val title: String,
    @Json(name = "view_count")
    val viewCount: Int
)