package com.example.swmandroid.model.community.jobposting


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class JobPostingItem(
    val career: String,
    val createdAt: String,
    val id: Int,
    @Json(name = "incruit_link")
    val incruitLink: String,
    @Json(name = "start_end_time")
    val startEndTime: String,
    val techStack: String,
    val text: String,
    val title: String,
    val viewCount: Int
) : Parcelable