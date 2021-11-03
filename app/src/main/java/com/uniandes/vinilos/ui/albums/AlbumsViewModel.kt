package com.uniandes.vinilos.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.data.model.AlbumModel
import com.uniandes.vinilos.domain.GetAlbumsUseCase
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    val listModel =MutableLiveData<List<AlbumModel>>()
    val isLoading = MutableLiveData<Boolean>()

    var getAlbumsUseCase =GetAlbumsUseCase()

    private val _text = MutableLiveData<String>().apply {
        value = "This is albums Fragment"
    }
    val text: LiveData<String> = _text

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result:List<AlbumModel> = getAlbumsUseCase()
            if(!result.isNullOrEmpty()){
                listModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}