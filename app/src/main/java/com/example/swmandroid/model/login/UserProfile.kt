package com.example.swmandroid.model.login

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("email") val email: String,
    @SerializedName("exp") val exp: Int,
    @SerializedName("nick_name") val nick_name: String,
    @SerializedName("tech_stack") val tech_stack: String,
    @SerializedName("tier") val tier: String,
    @SerializedName("user_role") val user_role: String,
    @SerializedName("user_token") val user_token: String,
)
