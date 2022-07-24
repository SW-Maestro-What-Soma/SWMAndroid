package com.example.swmandroid.di

import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.login.profile.ProfileRepository
import com.example.swmandroid.data.repository.login.profile.ProfileRepositoryImpl
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.example.swmandroid.screen.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appModule = module {

    viewModel { LoginViewModel(get(), get()) }
    viewModel { EasyLearningViewModel() }

    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    single { GoogleRepository() }

    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }

    single(named("Login")) { provideLoginRetrofit(get(), get()) }
    single { provideLoginApiService(get(qualifier = named("Login"))) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}