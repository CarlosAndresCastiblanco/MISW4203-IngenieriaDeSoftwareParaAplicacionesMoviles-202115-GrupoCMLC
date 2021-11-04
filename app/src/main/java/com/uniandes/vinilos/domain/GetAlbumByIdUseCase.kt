package com.uniandes.vinilos.domain

import com.uniandes.vinilos.data.Repository
import com.uniandes.vinilos.data.model.AlbumModel

class GetAlbumByIdUseCase {
    private val repository = Repository()

    suspend operator fun invoke(id:Int):AlbumModel = repository.getAlbumById(id)
}