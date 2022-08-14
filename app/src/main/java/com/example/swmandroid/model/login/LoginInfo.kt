package com.example.swmandroid.model.login

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("email") val email: String,
    @SerializedName("user_pw") val user_pw: String,
)
