package com.github.labibmuhajir.myapplication.data.repository

import com.github.labibmuhajir.myapplication.data.http.Service
import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ArtisanDataSource {
    suspend fun getArtisan(id: String): Artisan
    suspend fun getArtisan(): List<Artisan>
}

class ArtisanRepository(private val service: Service) : ArtisanDataSource {
    override suspend fun getArtisan(id: String): Artisan {
        return withContext(Dispatchers.IO) {
            service.getArtisan(id)
        }
    }

    override suspend fun getArtisan(): List<Artisan> {
        return withContext(Dispatchers.IO) {
            service.getArtisan()
        }
    }
}