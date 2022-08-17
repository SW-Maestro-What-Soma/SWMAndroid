package com.example.swmandroid.data.repository

import com.example.swmandroid.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepo() {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    val errorResponse = response.errorBody()
                    Resource.Error(errorMessage = errorResponse?.toString() ?: "Something went wrong")
                }
            } catch (e: HttpException) {
                Resource.Error(errorMessage = "Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }

}