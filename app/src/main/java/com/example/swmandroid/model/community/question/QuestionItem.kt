package com.example.swmandroid.model.community.question


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionItem(
    @Json(name = "comment_count")
    val commentCount: Int,
    val createdAt: String,
    @Json(name = "fk_user_id")
    val fkUserId: Int,
    val id: Int,
    val techStack: String,
    val text: String,
    val title: String,
    val viewCount: Int,
    @Json(name = "vote_count")
    val voteCount: String
)