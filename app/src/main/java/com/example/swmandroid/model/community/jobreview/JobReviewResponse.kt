package com.example.swmandroid.model.community.jobreview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobReviewResponse(
    val jobReviewList: JobReviewList
)