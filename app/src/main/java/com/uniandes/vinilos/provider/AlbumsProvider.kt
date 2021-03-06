package com.uniandes.vinilos.provider

import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.AlbumCreate
import com.uniandes.vinilos.model.Comment
import com.uniandes.vinilos.model.CommentResponse
import retrofit2.Response
import retrofit2.http.*

interface AlbumsProvider {

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") country: String): Response<Album>

    @POST("albums")
    suspend fun createAlbum(@Body album:AlbumCreate): Response<Album>

    @POST("albums/{id}/comments")
    suspend fun createComment(@Body comment:Comment,@Path("id") id:String): Response<CommentResponse>


}