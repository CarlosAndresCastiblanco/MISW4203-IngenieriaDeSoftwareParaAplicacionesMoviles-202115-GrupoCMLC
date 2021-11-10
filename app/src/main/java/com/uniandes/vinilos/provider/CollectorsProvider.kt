package com.uniandes.vinilos.provider

import com.uniandes.vinilos.model.Collector
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorsProvider {
    @GET("collectors")
    suspend fun getCollectors(): Response<List<Collector>>

    @GET("collectors/{id}")
    suspend fun getCollectorDetail(@Path("id") country: String): Response<Collector>
}