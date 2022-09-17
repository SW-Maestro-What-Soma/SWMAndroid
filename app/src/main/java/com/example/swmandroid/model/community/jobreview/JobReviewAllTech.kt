package com.example.swmandroid.model.community.jobreview

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobReviewAllTech(
    @Json(name = "Backend")
    val backend : List<JobReviewItem>,
    @Json(name = "Frontend")
    val frontend : List<JobReviewItem>,
    @Json(name = "Android")
    val android : List<JobReviewItem>,
    @Json(name = "iOS")
    val ios : List<JobReviewItem>,
    @Json(name = "DataScience")
    val dataScience : List<JobReviewItem>,
    @Json(name = "DataAnalysis")
    val dataAnalysis : List<JobReviewItem>,
    @Json(name = "Algorithm")
    val algorithm : List<JobReviewItem>,
    @Json(name = "DataStructure")
    val dataStructure : List<JobReviewItem>,
    @Json(name = "Network")
    val network : List<JobReviewItem>,
    @Json(name = "OperatingSystem")
    val operatingSystem : List<JobReviewItem>,
)
