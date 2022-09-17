package com.example.swmandroid.model.community.jobposting

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobPostingAllTech(
    @Json(name = "Backend")
    val backend : List<JobPostingItem>,
    @Json(name = "Frontend")
    val frontend : List<JobPostingItem>,
    @Json(name = "Android")
    val android : List<JobPostingItem>,
    @Json(name = "iOS")
    val ios : List<JobPostingItem>,
    @Json(name = "DataScience")
    val dataScience : List<JobPostingItem>,
    @Json(name = "DataAnalysis")
    val dataAnalysis : List<JobPostingItem>,
    @Json(name = "Algorithm")
    val algorithm : List<JobPostingItem>,
    @Json(name = "DataStructure")
    val dataStructure : List<JobPostingItem>,
    @Json(name = "Network")
    val network : List<JobPostingItem>,
    @Json(name = "OperatingSystem")
    val operatingSystem : List<JobPostingItem>,
)
