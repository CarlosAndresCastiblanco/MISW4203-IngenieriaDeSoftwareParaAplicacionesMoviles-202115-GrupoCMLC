package com.uniandes.vinilos.ui.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.data.model.AlbumModel
import com.uniandes.vinilos.domain.GetAlbumByIdUseCase
import kotlinx.coroutines.launch

class AlbumDetailViewModel: ViewModel() {

    val album = MutableLiveData<AlbumModel>()
    var getAlbumByIdUseCase = GetAlbumByIdUseCase()

    fun onCreate(){
        viewModelScope.launch {
            val result: AlbumModel = getAlbumByIdUseCase(100)
            if(result!=null){
                album.postValue(result)
            }
        }
    }

}