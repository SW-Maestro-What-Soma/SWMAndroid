package com.example.swmandroid.data.repository.login.profile

import com.example.swmandroid.data.network.ProfileApiService
import com.example.swmandroid.model.login.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val profileApiService: ProfileApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {
    override suspend fun getProfile(): LoginResponse? = withContext(ioDispatcher) {
        val response = profileApiService.getProfile()
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}