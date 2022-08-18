package com.example.swmandroid.di

import androidx.lifecycle.SavedStateHandle
import com.example.swmandroid.data.repository.login.email.LoginRepository
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.problem.ProblemRepository
import com.example.swmandroid.ui.easylearning.EasyLearningViewModel
import com.example.swmandroid.ui.login.LoginViewModel
import com.example.swmandroid.ui.test.TestViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { LoginViewModel(get(), get()) }
    single { (state: SavedStateHandle) -> EasyLearningViewModel(state, get()) }
    viewModel { (state: SavedStateHandle) -> TestViewModel(state) }

    single<LoginRepository> { LoginRepository(get()) }
    single { GoogleRepository() }
    single { ProblemRepository(get()) }

    single { provideMoshiConverterFactory() }
    single { buildOkHttpClient() }

    single { provideRetrofit(get(), get()) }
    single { provideLoginApiService(get()) }
    single { provideProblemApiService(get()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}