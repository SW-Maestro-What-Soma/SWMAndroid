package com.example.swmandroid.model.community.study


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudyResponse(
    val studyList: StudyList
)