package com.github.labibmuhajir.myapplication.ui.artisandetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.myapplication.common.orUnknownError
import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.data.repository.ArtisanDataSource
import kotlinx.coroutines.launch

class ArtisanDetailViewModel(private val artisanRepository: ArtisanDataSource) : ViewModel() {
    private val _artisanState = MutableLiveData<ViewState<Artisan>>()
    val artisanState get() = _artisanState

    fun getArtisan(id: String) {
        viewModelScope.launch {
            _artisanState.postValue(ViewState.Loading())
            try {
                val artisan = artisanRepository.getArtisan(id)
                _artisanState.postValue(ViewState.Success(artisan))
            } catch (e: Exception) {
                _artisanState.postValue(ViewState.Error(orUnknownError(e.message)))
            }
        }
    }
}