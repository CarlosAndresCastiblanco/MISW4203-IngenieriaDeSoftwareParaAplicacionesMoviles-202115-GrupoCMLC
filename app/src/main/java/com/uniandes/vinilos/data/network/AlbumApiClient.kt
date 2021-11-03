package com.uniandes.vinilos.data.network

import com.uniandes.vinilos.data.model.AlbumModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApiClient {

    @GET("/albums")
    suspend fun getAllAlbums():Response<List<AlbumModel>>

    @GET("/albums/{id}")
    suspend fun getAlbumById(@Path("id")id:Int):Response<AlbumModel>
}