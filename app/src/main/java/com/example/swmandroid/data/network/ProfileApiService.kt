package com.example.swmandroid.data.network

import com.example.swmandroid.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApiService {

    @GET("/user")
    suspend fun getProfile() : Response<LoginResponse>

}