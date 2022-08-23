package com.example.swmandroid.model.community.jobposting

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobPostingResponse(
    val jobPostingList: JobPostingList
)