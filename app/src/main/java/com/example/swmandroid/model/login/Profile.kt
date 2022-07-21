package com.example.swmandroid.model.login


import com.google.gson.annotations.SerializedName

data class Profile(
    val id: String,
    @SerializedName("teck_stack")
    val teckStack: String,
    val userId: String,
    val username: String
)