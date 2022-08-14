package com.example.swmandroid.di

import com.example.swmandroid.BuildConfig
import com.example.swmandroid.data.network.LoginApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideLoginApiService(retrofit : Retrofit) : LoginApiService {
    return retrofit.create(LoginApiService::class.java)
}

fun provideLoginRetrofit(
    okHttpClient : OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BACK_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConverterFactory() : GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun buildOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}