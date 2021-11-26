package com.uniandes.vinilos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Artist
import com.uniandes.vinilos.repository.AlbumsRepository
import com.uniandes.vinilos.repository.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ArtistsListScreenViewModel @Inject constructor(
    private val repository: ArtistsRepository
) : ViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()

    fun getArtists(): LiveData<List<Artist>> {
        viewModelScope.launch(Dispatchers.IO) {
            val artist = repository.getArtists()
            _artists.postValue(artist)
        }
        return _artists
    }
}