package com.example.swmandroid.data.repository.login.login

import com.example.swmandroid.data.network.LoginApiService
import com.example.swmandroid.model.login.UserEntity
import okhttp3.ResponseBody
import retrofit2.Response

class LoginRepositoryImpl(
    private val profileApiService: LoginApiService
) : LoginRepository {

    override suspend fun postSignUp(userEntity: UserEntity): Response<ResponseBody>  {
        return profileApiService.postSignUp(userEntity)
    }
}