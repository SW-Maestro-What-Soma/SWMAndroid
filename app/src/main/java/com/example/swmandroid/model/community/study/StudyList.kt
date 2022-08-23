package com.example.swmandroid.model.community.study

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudyList(
    val content: List<StudyItem>
)