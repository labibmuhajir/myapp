package com.github.labibmuhajir.myapplication.di

import com.github.labibmuhajir.myapplication.data.local.PrefManager
import com.github.labibmuhajir.myapplication.data.http.Service
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://604048b4f34cf600173c7cda.mockapi.io/api/"

val appModule = module {
    single { Gson() }
    single<Converter.Factory> { GsonConverterFactory.create() }
    single { OkHttpClient.Builder().build() }
    single { Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(get())
        .addConverterFactory(get())
        .build()
    }
    single<Service> {
        val retrofit: Retrofit = get()
        retrofit.create(Service::class.java)
    }
    single { PrefManager(get(), get()) }
}