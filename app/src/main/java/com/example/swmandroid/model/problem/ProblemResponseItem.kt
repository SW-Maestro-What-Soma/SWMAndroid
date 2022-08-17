package com.example.swmandroid.model.problem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProblemResponseItem(
    @SerializedName("text")
    val problemTitle : String,
    @SerializedName("model_answer")
    val problemContent : String,
) : Parcelable