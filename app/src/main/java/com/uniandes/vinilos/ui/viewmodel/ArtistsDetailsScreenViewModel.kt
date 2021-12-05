package com.uniandes.vinilos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.model.Artist
import com.uniandes.vinilos.repository.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ArtistsDetailsScreenViewModel @Inject constructor(
    private val repository: ArtistsRepository
) : ViewModel() {

    private val _artist = MutableLiveData<Artist>()

    fun getArtistById(id:String): MutableLiveData<Artist> {
        viewModelScope.launch(Dispatchers.IO) {
            val artist = repository.getArtist(id)
            _artist.postValue(artist)
        }
        return _artist
    }
}