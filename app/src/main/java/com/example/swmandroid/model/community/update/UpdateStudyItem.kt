package com.example.swmandroid.model.community.update

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateStudyItem(
    @Json(name = "day_of_the_week")
    val dayOfTheWeek: String,
    val id: Int,
    @Json(name = "max_grade")
    val maxGrade: String,
    @Json(name = "meeting_link")
    val meetingLink: String,
    @Json(name = "min_grade")
    val minGrade: String,
    @Json(name = "on_offline")
    val onOffLine: Boolean,
    @Json(name = "per_week")
    val perWeek: Int,
    @Json(name = "tech_stack")
    val techStack: String,
    val text: String,
    val title: String,
    @Json(name = "user_email")
    val userEmail: String,
)
