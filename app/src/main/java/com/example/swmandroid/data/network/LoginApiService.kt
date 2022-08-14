package com.example.swmandroid.data.network

import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @Headers("Content-Type: application/json")
    @POST("/user/signUp")
    suspend fun postSignUp(@Body userEntity: UserEntity): Response<ResponseBody>

    @Headers("accept: application/json", "Content-Type: application/json")
    @POST("/user/login")
    suspend fun postLogin(@Body loginInfo: LoginInfo): Response<UserProfile>

}