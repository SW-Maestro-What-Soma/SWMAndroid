package com.example.swmandroid.model.login

data class LoginResponseItem(
    val id: String,
    val profile: List<Profile>,
    val status: Int
)