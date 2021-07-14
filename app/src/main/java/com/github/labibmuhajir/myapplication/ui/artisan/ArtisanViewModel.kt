package com.github.labibmuhajir.myapplication.ui.artisan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.myapplication.common.orUnknownError
import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.data.repository.ArtisanDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ArtisanViewModel(private val artisanRepository: ArtisanDataSource) : ViewModel() {
    private val _artisanState = MutableLiveData<ViewState<List<Artisan>>>()
    val artisan get() = _artisanState
    private lateinit var artisanData: List<Artisan>

    fun getArtisan() {
        viewModelScope.launch {
            try {
                _artisanState.postValue(ViewState.Loading())
                artisanData = artisanRepository.getArtisan()
                _artisanState.postValue(ViewState.Success(artisanData))
            } catch (e: Exception) {
                _artisanState.postValue(ViewState.Error(orUnknownError(e.message)))
            }
        }
    }

    fun search(key: String?) {
        viewModelScope.launch {
            try {
                key?.let {
                    flowOf(artisanData).map { it.filter { it.name.contains(key) } }.collect {
                        _artisanState.postValue(ViewState.Success(it))
                    }
                } ?: kotlin.run {
                    _artisanState.postValue(ViewState.Success(artisanData))
                }
            } catch (e: Exception) {
                _artisanState.postValue(ViewState.Error(orUnknownError(e.message)))
            }
        }
    }
}