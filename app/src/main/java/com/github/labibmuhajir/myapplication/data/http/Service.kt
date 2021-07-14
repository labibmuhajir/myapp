package com.github.labibmuhajir.myapplication.data.http

import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("v1/list-artisan")
    suspend fun getArtisan(): List<Artisan>

    @GET("v1/list-artisan/{id}")
    suspend fun getArtisan(@Path("id") id: String): Artisan
}