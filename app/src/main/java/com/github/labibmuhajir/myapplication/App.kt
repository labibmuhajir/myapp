package com.github.labibmuhajir.myapplication

import android.app.Application
import com.github.labibmuhajir.myapplication.di.appModule
import com.github.labibmuhajir.myapplication.di.repositoryModule
import com.github.labibmuhajir.myapplication.di.viewModuleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, repositoryModule, viewModuleModule)
        }
    }
}