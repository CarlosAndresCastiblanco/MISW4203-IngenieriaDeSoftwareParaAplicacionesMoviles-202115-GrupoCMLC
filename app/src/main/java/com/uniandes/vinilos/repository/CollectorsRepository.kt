package com.uniandes.vinilos.repository

import com.uniandes.vinilos.model.Collector
import com.uniandes.vinilos.model.CollectorAlbums
import com.uniandes.vinilos.model.CollectorPerformers
import com.uniandes.vinilos.provider.CollectorCache
import com.uniandes.vinilos.provider.CollectorsProvider
import javax.inject.Inject

interface CollectorsRepository {
    suspend fun getCollectors(): List<Collector>
    suspend fun getCollector(id: String): Collector
}

class CollectorsRepositoryImp @Inject constructor(
    private val collectorsProvider: CollectorsProvider
): CollectorsRepository {

    private var collectors: List<Collector> = emptyList()
    private var collector: Collector? = null

    override suspend fun getCollectors(): List<Collector> {
        if(CollectorCache.getCollector().isEmpty()){
            val apiResponse = collectorsProvider.getCollectors().body()
            collectors = apiResponse ?: emptyList()
            CollectorCache.setCollectors(collectors)
            return collectors
        }else{
            return CollectorCache.getCollector()
        }
    }

    override suspend fun getCollector(id: String): Collector {
        val apiResponse = collectorsProvider.getCollectorDetail(id).body()

        var collectorPerfomers = arrayListOf(
            CollectorPerformers("1", "Artista", "http:asd.com")
        )
        var collectorAlbums = arrayListOf(
            CollectorAlbums("1", "590", "Active")
        )
        collector = apiResponse ?: Collector("1", "Performer", "1234567", "performer@music.com",
            collectorPerfomers, collectorAlbums)
        return collector as Collector
    }

}
