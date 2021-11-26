package com.uniandes.vinilos.repository

import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.provider.ArtistsProvider
import javax.inject.Inject

interface ArtistsRepository {
    suspend fun getArtists(): List<Artist>

}

class ArtistsRepositoryImp @Inject constructor(
    private val artistsProvider: ArtistsProvider
): ArtistsRepository {

    private var artists: List<Artist> = emptyList()

    override suspend fun getArtists(): List<Artist> {
        val apiResponse = artistsProvider.getArtists().body()
        artists = apiResponse ?: emptyList()
        return artists;
    }


}

