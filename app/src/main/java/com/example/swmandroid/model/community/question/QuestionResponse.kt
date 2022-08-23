package com.example.swmandroid.model.community.question


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionResponse(
    val nowQnaList: NowQnaList
)