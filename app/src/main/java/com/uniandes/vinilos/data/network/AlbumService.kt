package com.uniandes.vinilos.data.network

import com.uniandes.vinilos.core.RetrofitHelper
import com.uniandes.vinilos.data.model.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AlbumService {

        private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAlbums():List<AlbumModel>{
        return withContext(Dispatchers.IO){
            val response: Response<List<AlbumModel>> = retrofit.create(AlbumApiClient::class.java).getAllAlbums()
                response.body() ?: emptyList()
        }
    }

    suspend fun  getAlbumById(id:Int):AlbumModel{
        return withContext(Dispatchers.IO){
            val response: Response<AlbumModel> = retrofit.create(AlbumApiClient::class.java).getAlbumById(id)
            response.body() ?: AlbumModel("","","","","","")
        }
    }

}