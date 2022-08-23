package com.example.swmandroid.model.community.jobposting

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobPostingList(
    val content: List<JobPostingItem>
)