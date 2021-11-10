package com.uniandes.vinilos.repository

import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.provider.CollectorsProvider
import javax.inject.Inject

interface CollectorsRepository {
    suspend fun getCollectors(): List<Collector>
}

class CollectorsRepositoryImp @Inject constructor(
    private val collectorsProvider: CollectorsProvider
): CollectorsRepository {

    private var collectors: List<Collector> = emptyList()

    override suspend fun getCollectors(): List<Collector> {
        val apiResponse = collectorsProvider.getCollectors().body()

        collectors = apiResponse ?: emptyList()
        return collectors
    }

}
