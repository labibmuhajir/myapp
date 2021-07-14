package com.github.labibmuhajir.myapplication.data.http.response

import com.google.gson.annotations.SerializedName

data class Artisan(
    val id: String,
    val createdAt: String,
    val name: String,
    val avatar: String,
    val image: String,
    @SerializedName("user_image")
    val userImage: String,
    val rating: String,
    val description: String,
    val services: List<Service>
)

data class Service(
    val name: String,
    val price: String,
    val caption: String
)