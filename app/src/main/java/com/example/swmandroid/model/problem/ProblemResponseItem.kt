package com.example.swmandroid.model.problem

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ProblemResponseItem(
    val id: Int,
    @Json(name = "model_answer")
    val modelAnswer: String,
    val techStack: String,
    val text: String
) : Parcelable