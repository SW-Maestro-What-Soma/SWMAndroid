package com.example.swmandroid.data.repository.login.profile

import com.example.swmandroid.model.login.LoginResponse

interface ProfileRepository {

    suspend fun getLoginInformation() : LoginResponse?

}