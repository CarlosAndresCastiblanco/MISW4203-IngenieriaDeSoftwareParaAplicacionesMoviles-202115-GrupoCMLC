package com.uniandes.vinilos.provider

import com.uniandes.vinilos.model.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsProvider {

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") country: String): Response<Album>
}