package com.uniandes.vinilos.repository

import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Performer
import com.uniandes.vinilos.provider.AlbumsProvider
import javax.inject.Inject

interface AlbumsRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(id: String): Album
}

class AlbumsRepositoryImp @Inject constructor(
    private val albumsProvider: AlbumsProvider
): AlbumsRepository {

    private var albums: List<Album> = emptyList()
    private var album: Album? = null

    override suspend fun getAlbums(): List<Album> {
        val apiResponse = albumsProvider.getAlbums().body()

        albums = apiResponse ?: emptyList()
        return albums
    }

    override suspend fun getAlbum(id: String): Album {
        val apiResponse = albumsProvider.getAlbumDetail(id).body()

        var perfomers = arrayListOf(
            Performer("1", "Artista")
        )
        album = apiResponse ?: Album("", "", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"
        , "", "", "", "", perfomers);
        return album as Album
    }

}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()