package com.example.swmandroid.model.login


import com.google.gson.annotations.SerializedName

data class LoginResponseItem(
    val id: String,
    val profile: List<Profile>,
    val status: Int
)