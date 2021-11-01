package com.uniandes.vinilos.data

import com.uniandes.vinilos.data.model.AlbumModel
import com.uniandes.vinilos.data.network.AlbumService

class Repository {


    private val albumApi = AlbumService()

    suspend fun getAllAlbums(): List<AlbumModel>{
        val response:List<AlbumModel> = albumApi.getAlbums()
        return response
    }
}