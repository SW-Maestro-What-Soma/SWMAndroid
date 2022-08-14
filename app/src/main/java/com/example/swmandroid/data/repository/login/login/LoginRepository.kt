package com.example.swmandroid.data.repository.login.login

import com.example.swmandroid.model.login.UserEntity
import okhttp3.ResponseBody
import retrofit2.Response

interface LoginRepository {

    suspend fun postSignUp(userEntity: UserEntity) : Response<ResponseBody>

}