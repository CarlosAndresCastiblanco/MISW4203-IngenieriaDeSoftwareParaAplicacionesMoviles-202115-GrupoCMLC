package com.uniandes.vinilos.repository

import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.provider.ArtistsProvider
import javax.inject.Inject

interface ArtistsRepository {
    suspend fun getArtists(): List<Artist>
    suspend fun getArtist(id: String): Artist

}

class ArtistsRepositoryImp @Inject constructor(
    private val artistsProvider: ArtistsProvider
): ArtistsRepository {

    private var artists: List<Artist> = emptyList()
    private var artist: Artist? = null

    override suspend fun getArtists(): List<Artist> {
        val apiResponse = artistsProvider.getArtists().body()
        artists = apiResponse ?: emptyList()
        return artists;
    }

    override suspend fun getArtist(id: String): Artist {
        val apiResponse = artistsProvider.getArtistById(id).body()
        artist = apiResponse ?: Artist("1", "Artista 1", "http//:image.com", "Artista del genero",
        "1948-07-16T00:00:00.000Z")
        return artist as Artist
    }


}

