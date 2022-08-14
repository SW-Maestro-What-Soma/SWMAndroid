package com.example.swmandroid.model.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    @SerializedName("email")
    val email: String,
    @SerializedName("nick_name")
    var nick_name: String = "",
    @SerializedName("tech_stack")
    var tech_stack: String = "",
    @SerializedName("user_pw")
    val user_pw: String,
) : Parcelable
