package com.github.labibmuhajir.myapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.myapplication.common.orUnknownError
import com.github.labibmuhajir.myapplication.data.model.User
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.data.repository.UserDataSource
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserDataSource) : ViewModel() {
    private val _loginState = MutableLiveData<ViewState<Boolean>>()
    val loginState get() = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.postValue(ViewState.Loading())
            try {
                val user = User(username)
                userRepository.setUser(user)
                _loginState.postValue(ViewState.Success(true))
            } catch (e: Exception) {
                _loginState.postValue(ViewState.Error(orUnknownError(e.message)))
            }
        }
    }
}