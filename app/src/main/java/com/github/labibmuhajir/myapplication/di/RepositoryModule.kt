package com.github.labibmuhajir.myapplication.di

import com.github.labibmuhajir.myapplication.data.repository.ArtisanDataSource
import com.github.labibmuhajir.myapplication.data.repository.ArtisanRepository
import com.github.labibmuhajir.myapplication.data.repository.UserDataSource
import com.github.labibmuhajir.myapplication.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDataSource> { UserRepository(get()) }
    single<ArtisanDataSource> { ArtisanRepository(get()) }
}