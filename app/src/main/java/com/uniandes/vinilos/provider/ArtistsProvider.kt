package com.uniandes.vinilos.provider

import com.uniandes.vinilos.model.*
import retrofit2.Response
import retrofit2.http.*

interface ArtistsProvider {

    @GET("musicians")
    suspend fun getArtists(): Response<List<Artist>>

    @GET("musicians/{id}")
    suspend fun getArtistById(@Path("id") id: String): Response<Artist>

}