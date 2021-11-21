package com.uniandes.vinilos.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.repository.AlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CreateAlbumViewModel @Inject constructor(
    private val repository: AlbumsRepository
): ViewModel() {

    val name = MutableLiveData<String>()
    val cover = MutableLiveData<String>()
    val genre = MutableLiveData<String>()
    val recorder = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val releaseDate = MutableLiveData<String>()
    val navegar = MutableLiveData<Boolean>()



    val valid = MediatorLiveData<Boolean>().apply{
        addSource(name){
            val valid = isFormValid()
            value = valid
        }
        addSource(cover){
            val valid = isFormValid()
            value = valid
        }
        addSource(genre){
            val valid = isFormValid()
            value = valid
        }
        addSource(description){
            val valid = isFormValid()
            value = valid
        }
        addSource(recorder){
            val valid = isFormValid()
            value = valid
        }
        addSource(releaseDate){
            val valid = isFormValid()
            value = valid
        }

    }

    fun isFormValid():Boolean{
           if(name.value.isNullOrEmpty()){
               return false
           }
        if(cover.value.isNullOrEmpty()){
            return false
        }
        if(description.value.isNullOrEmpty()){
            return false
        }
        if(genre.value.isNullOrEmpty()){
            return false
        }
        if(recorder.value.isNullOrEmpty()){
            return false
        }
        if(releaseDate.value.isNullOrEmpty()){
            return false
        }
        return true
    }


    fun enviar():String{
        //val apiFormat  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        //val date =  LocalDate.parse(releaseDate.value, apiFormat)
        val newAlbum = Album(
            "0",
            name.value?:"",
            cover.value?:"",
            releaseDate.value?:"",
            genre.value?:"",
            recorder.value?:"",
            description.value?:"",
            emptyList()
            )
        Log.d("DATE",releaseDate.value.toString())
        viewModelScope.launch(Dispatchers.IO) {
            val newalbum = repository.createAlbum(newAlbum)
            Log.d("RESPONSE",newalbum.toString())
            if(newalbum.id != "error"){
                navegar.postValue(true)
            }
        }
        return ""
    }

}