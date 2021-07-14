package com.github.labibmuhajir.myapplication.di

import com.github.labibmuhajir.myapplication.ui.artisan.ArtisanViewModel
import com.github.labibmuhajir.myapplication.ui.artisandetail.ArtisanDetailViewModel
import com.github.labibmuhajir.myapplication.ui.login.LoginViewModel
import com.github.labibmuhajir.myapplication.ui.main.MainViewModel
import com.github.labibmuhajir.myapplication.ui.profile.ProfileViewModel
import com.github.labibmuhajir.myapplication.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { ArtisanDetailViewModel(get()) }
    viewModel { ArtisanViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}