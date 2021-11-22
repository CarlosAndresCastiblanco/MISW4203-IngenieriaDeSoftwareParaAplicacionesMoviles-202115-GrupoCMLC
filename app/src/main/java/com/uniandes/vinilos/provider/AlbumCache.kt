package com.uniandes.vinilos.provider

import android.util.Log
import com.uniandes.vinilos.model.Album

object AlbumCache {
    private var albums:List<Album> = listOf()

    fun getAlbum() = this.albums
    fun setAlbum(value:List<Album>) {
        this.albums = value
    }

    fun addAlbum(value:Album) {
        this.albums = listOf()
    }
}