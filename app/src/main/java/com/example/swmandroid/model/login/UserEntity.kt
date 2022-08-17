package com.example.swmandroid.model.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val email: String,
    var nick_name: String = "",
    var tech_stack: String = "",
    val user_pw: String,
) : Parcelable
