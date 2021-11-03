package com.uniandes.vinilos.domain

import com.uniandes.vinilos.data.Repository
import com.uniandes.vinilos.data.model.AlbumModel

class GetAlbumsUseCase {
    private val repository = Repository()

    suspend operator fun invoke():List<AlbumModel> = repository.getAllAlbums()
}