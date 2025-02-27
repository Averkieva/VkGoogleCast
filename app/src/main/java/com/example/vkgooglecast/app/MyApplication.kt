package com.example.vkgooglecast.app

import com.example.vkgooglecast.di.appModule
import org.koin.core.context.startKoin

class MyApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}