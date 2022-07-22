package com.example.swmandroid

import android.app.Application
import com.example.swmandroid.di.appModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GlobalApplication)
            modules(appModule)
        }
    }
}