package com.example.swmandroid.data.repository.login.email

import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import okhttp3.ResponseBody
import retrofit2.Response

interface LoginRepository {

    suspend fun postSignUp(userEntity: UserEntity) : Response<ResponseBody>

    suspend fun postLogin(loginInfo: LoginInfo) : Response<UserProfile>

}