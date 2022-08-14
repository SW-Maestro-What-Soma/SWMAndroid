package com.example.swmandroid.data.repository.login.login

import com.example.swmandroid.data.network.LoginApiService
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import okhttp3.ResponseBody
import retrofit2.Response

class LoginRepositoryImpl(
    private val profileApiService: LoginApiService
) : LoginRepository {

    override suspend fun postSignUp(userEntity: UserEntity): Response<ResponseBody> = profileApiService.postSignUp(userEntity)

    override suspend fun postLogin(loginInfo: LoginInfo): Response<UserProfile> = profileApiService.postLogin(loginInfo)

}