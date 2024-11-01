package com.example.recipesapp.presentation

import android.app.Application
import com.example.recipesapp.presentation.di.appModule
import com.example.recipesapp.presentation.di.dataModule
import com.example.recipesapp.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(dataModule, domainModule, appModule))
        }
    }
}