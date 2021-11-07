package com.uniandes.vinilos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.repository.AlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class AlbumDetailsScreenViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    private val _albums = MutableLiveData<Album>()

    fun getAlbumById(id:String): LiveData<Album> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getAlbum(id)
            _albums.postValue(news)
        }
        return _albums
    }
}