package com.uniandes.vinilos.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Comment
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
    val rating = MutableLiveData<Int>()
    val comment = MutableLiveData<String>()

    val valid = MediatorLiveData<Boolean>().apply {
        addSource(rating){
            val valid =  isFormValid()
            value = valid
        }
        addSource(comment){
            val valid =  isFormValid()
            value = valid
        }
    }

    fun isFormValid():Boolean{
        if(comment.value.isNullOrEmpty()){
            return false
        }
        if(rating.value.toString().isNullOrEmpty()){
            return false
        }
        return true
    }
    fun comentar(album_id:String):String{
        viewModelScope.launch(Dispatchers.IO) {
            val newComment = Comment( description= comment.value?:"",rating=rating.value?:1,collector = "100")
            val response = repository.createComment(album_id,newComment)
            if(!response.description.isNullOrEmpty()){
                comment.postValue("")
                rating.postValue(1)
            }
        }
        return ""
    }

    fun getAlbumById(id:String): LiveData<Album> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getAlbum(id)
            _albums.postValue(news)
        }
        return _albums
    }
}