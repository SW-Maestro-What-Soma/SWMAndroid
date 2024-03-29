package com.example.swmandroid.model.community.study

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class StudyItem(
    @Json(name = "comment_count")
    val commentCount: Int,
    val createdAt: String,
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
    val onOffline: Boolean,
    @Json(name = "per_week")
    val perWeek: Int,
    val techStack: String,
    val text: String,
    val title: String,
    @Json(name = "user_id")
    val userId: Int,
    val viewCount: Int,
    @Json(name = "user_email")
    val userEmail: String,
    @Json(name = "vote_count")
    val voteCount: Int,
) : Parcelable