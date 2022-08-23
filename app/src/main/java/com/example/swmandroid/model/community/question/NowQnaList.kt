package com.example.swmandroid.model.community.question

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowQnaList(
    val content: List<QuestionItem>
)