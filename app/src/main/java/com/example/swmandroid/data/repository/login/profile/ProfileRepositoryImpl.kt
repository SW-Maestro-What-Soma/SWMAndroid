package com.example.swmandroid.data.repository.login.profile

import com.example.swmandroid.data.network.LoginApiService
import com.example.swmandroid.model.login.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val loginApiService: LoginApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {
    override suspend fun getLoginInformation(): LoginResponse? = withContext(ioDispatcher) {
        val response = loginApiService.getLogin()
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}