package com.github.labibmuhajir.myapplication.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.data.repository.UserDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class LoginState {
    LOGGED_IN, LOGGED_OUT
}

class SplashViewModel(private val userRepository: UserDataSource) : ViewModel() {
    private val _loginState = MutableLiveData<ViewState<LoginState>>()
    val loginState get() = _loginState

    fun isLoggedIn() {
        viewModelScope.launch {
            loginState.postValue(ViewState.Loading())
            try {
                val isLoggedIn = userRepository.getUser() != null
                delay(500)
                loginState.postValue(
                    ViewState.Success(
                        if (isLoggedIn) LoginState.LOGGED_IN else LoginState.LOGGED_OUT
                    )
                )
            } catch (e: Exception) {
                loginState.postValue(
                    ViewState.Success(LoginState.LOGGED_OUT)
                )
            }
        }
    }
}