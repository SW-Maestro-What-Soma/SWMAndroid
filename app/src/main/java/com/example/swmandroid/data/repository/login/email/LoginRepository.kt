package com.example.swmandroid.data.repository.login.email

import com.example.swmandroid.data.network.LoginApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.login.EmailConfirm
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import com.example.swmandroid.util.Resource
import okhttp3.ResponseBody

class LoginRepository(private val profileApiService: LoginApiService) : BaseRepo() {

    suspend fun postSignUp(userEntity: UserEntity): Resource<ResponseBody> = safeApiCall { profileApiService.postSignUp(userEntity) }

    suspend fun postLogin(loginInfo: LoginInfo): Resource<UserProfile> = safeApiCall { profileApiService.postLogin(loginInfo) }

    suspend fun postEmailConfirm(userEmail : String) : Resource<EmailConfirm> = safeApiCall { profileApiService.postEmailConfirm(userEmail) }

}