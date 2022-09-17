package com.example.swmandroid.model.community.question

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionAllTech(
    @Json(name = "Backend")
    val backend: List<QuestionItem>,
    @Json(name = "Frontend")
    val frontend: List<QuestionItem>,
    @Json(name = "Android")
    val android: List<QuestionItem>,
    @Json(name = "iOS")
    val ios: List<QuestionItem>,
    @Json(name = "DataScience")
    val dataScience: List<QuestionItem>,
    @Json(name = "DataAnalysis")
    val dataAnalysis: List<QuestionItem>,
    @Json(name = "Algorithm")
    val algorithm: List<QuestionItem>,
    @Json(name = "DataStructure")
    val dataStructure: List<QuestionItem>,
    @Json(name = "Network")
    val network: List<QuestionItem>,
    @Json(name = "OperatingSystem")
    val operatingSystem: List<QuestionItem>,
)
