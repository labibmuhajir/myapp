package com.github.labibmuhajir.myapplication.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.myapplication.common.orUnknownError
import com.github.labibmuhajir.myapplication.data.model.User
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.data.repository.UserDataSource
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserDataSource) : ViewModel() {
    private val _userState = MutableLiveData<ViewState<User>>()
    val userState get() = _userState

    fun getUser() {
        viewModelScope.launch {
            _userState.postValue(ViewState.Loading())
            try {
                val user = userRepository.getUser()!!
                _userState.postValue(ViewState.Success(user))
            } catch (e: Exception) {
                _userState.postValue(ViewState.Error(orUnknownError(e.message)))
            }
        }
    }
}