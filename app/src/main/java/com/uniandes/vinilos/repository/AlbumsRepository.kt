package com.uniandes.vinilos.repository

import android.util.Log
import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.provider.AlbumCache
import com.uniandes.vinilos.provider.AlbumsProvider
import javax.inject.Inject

interface AlbumsRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(id: String): Album
    suspend fun createAlbum(album:Album):Album
    suspend fun createComment(album_id:String,comment:Comment):CommentResponse
}

class AlbumsRepositoryImp @Inject constructor(
    private val albumsProvider: AlbumsProvider
): AlbumsRepository {

    private var albums: List<Album> = emptyList()
    private var album: Album? = null

    override suspend fun getAlbums(): List<Album> {
        if(AlbumCache.getAlbum().isEmpty()){
            val apiResponse = albumsProvider.getAlbums().body()
            albums = apiResponse ?: emptyList()
            AlbumCache.setAlbum(albums)
            return albums
        }else{
            return AlbumCache.getAlbum()
        }

    }

    override suspend fun getAlbum(id: String): Album {
        val apiResponse = albumsProvider.getAlbumDetail(id).body()

        var perfomers = arrayListOf(
            Performer("1", "Artista")
        )
        album = apiResponse ?: Album("", "", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"
        , "", "", "", "", perfomers, emptyList())
        return album as Album
    }

     override  suspend fun  createAlbum(album:Album):Album{

        val creatingAlbum = AlbumCreate(name=album.name,cover=album.cover,releaseDate =album.releaseDate,genre = album.genre,recordLabel = album.recordLabel,description = album.description)
        val apiResponse = albumsProvider.createAlbum(creatingAlbum)
        var perfomers = arrayListOf(
            Performer("1", "Artista")
        )
        var newAlbum = apiResponse.body() ?: Album("error", "", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"
            , "", "", "", "", perfomers,emptyList())
         AlbumCache.addAlbum(newAlbum)
        return newAlbum as Album
    }

    override suspend fun createComment(album_id:String, comment: Comment): CommentResponse {
        val apiResponse = albumsProvider.createComment(comment,album_id)
        return apiResponse.body() as CommentResponse
    }

}

