package com.example.swmandroid.model.login

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserEntity(
    val email: String,
    var nick_name: String = "",
    var tech_stack: String = "",
    val user_pw: String,
) : Parcelable
