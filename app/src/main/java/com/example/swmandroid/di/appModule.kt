package com.example.swmandroid.di

import androidx.lifecycle.SavedStateHandle
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.login.login.LoginRepository
import com.example.swmandroid.data.repository.login.login.LoginRepositoryImpl
import com.example.swmandroid.ui.easylearning.EasyLearningViewModel
import com.example.swmandroid.ui.login.LoginViewModel
import com.example.swmandroid.ui.test.TestViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appModule = module {

    viewModel { LoginViewModel(get(), get()) }
    viewModel { (state: SavedStateHandle) -> EasyLearningViewModel(state) }
    viewModel { (state: SavedStateHandle) -> TestViewModel(state) }

    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single { GoogleRepository() }

    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }

    single(named("Login")) { provideLoginRetrofit(get(), get()) }
    single { provideLoginApiService(get(qualifier = named("Login"))) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}