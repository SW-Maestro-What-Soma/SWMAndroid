package com.example.swmandroid.data.network

import com.example.swmandroid.model.login.LoginResponse
import com.example.swmandroid.model.login.UserEntity
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @GET("/user")
    suspend fun getProfile(): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/user/signUp")
    suspend fun postSignUp(
        @Body userEntity: UserEntity
    ): Response<ResponseBody>
}