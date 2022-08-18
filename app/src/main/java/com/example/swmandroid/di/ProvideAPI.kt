package com.example.swmandroid.di

import com.example.swmandroid.BuildConfig
import com.example.swmandroid.data.network.LoginApiService
import com.example.swmandroid.data.network.ProblemApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideLoginApiService(retrofit : Retrofit) : LoginApiService {
    return retrofit.create(LoginApiService::class.java)
}

fun provideProblemApiService(retrofit: Retrofit) : ProblemApiService{
    return retrofit.create(ProblemApiService::class.java)
}

fun provideRetrofit(
    okHttpClient : OkHttpClient,
    moshi: Moshi
) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BACK_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}

fun provideMoshiConverterFactory() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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