package com.uniandes.vinilos

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
class ListScreenViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    private val _news = MutableLiveData<List<Album>>()

    fun getAlbums(): LiveData<List<Album>> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getAlbums()
            _news.postValue(news)
        }
        return _news
    }
}