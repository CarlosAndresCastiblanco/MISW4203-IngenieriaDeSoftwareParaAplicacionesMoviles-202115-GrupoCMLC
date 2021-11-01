package com.uniandes.vinilos.data.network

import com.uniandes.vinilos.data.model.AlbumModel
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApiClient {

    @GET("/albums")
    suspend fun getAllAlbums():Response<List<AlbumModel>>
}