package com.example.swmandroid

import android.app.Application
import com.example.swmandroid.di.appModule
import com.example.swmandroid.util.DataStoreModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GlobalApplication : Application() {

    private lateinit var dataStoreModule: DataStoreModule

    companion object {
        private lateinit var globalApplication: GlobalApplication
        fun getInstance(): GlobalApplication = globalApplication
    }

    override fun onCreate() {
        super.onCreate()

        globalApplication = this
        dataStoreModule = DataStoreModule(this)

        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_API_KEY)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GlobalApplication)
            modules(appModule)
        }
    }

    fun getDataStore() : DataStoreModule = dataStoreModule

}