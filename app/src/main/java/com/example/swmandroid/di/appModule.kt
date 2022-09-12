package com.example.swmandroid.di

import androidx.lifecycle.SavedStateHandle
import com.example.swmandroid.data.repository.community.CommunityRepository
import com.example.swmandroid.data.repository.community.RecentSearchRepository
import com.example.swmandroid.data.repository.login.email.LoginRepository
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.problem.ProblemRepository
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.ui.easylearning.EasyLearningViewModel
import com.example.swmandroid.ui.login.LoginViewModel
import com.example.swmandroid.ui.test.TestViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    // ViewModel
    viewModel { LoginViewModel(get(), get()) }
    viewModel { (state: SavedStateHandle) -> EasyLearningViewModel(state, get()) }
    viewModel { (state: SavedStateHandle) -> TestViewModel(state, get()) }
    viewModel { CommunityViewModel(get(), get()) }

    // Repository
    single { LoginRepository(get()) }
    single { GoogleRepository() }
    single { ProblemRepository(get()) }
    single { RecentSearchRepository(get(), get()) }
    single { CommunityRepository(get()) }

    // Retrofit
    single { provideMoshiConverterFactory() }
    single { buildOkHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideLoginApiService(get()) }
    single { provideProblemApiService(get()) }
    single { provideCommunityApiService(get()) }

    // Coroutine
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    // DB
    single { provideDB(androidApplication()) }
    single { provideRecentSearchDao(get()) }

}