package com.example.swmandroid.model.community.update

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateJobReviewItem(
    val id : Int,
    @Json(name = "pass_fail")
    val passFail : Boolean,
    @Json(name = "process_category")
    val processCategory : String,
    @Json(name = "tech_stack")
    val techStack : String,
    val text : String,
    val title : String,
    @Json(name = "user_email")
    val userEmail : String,
)
