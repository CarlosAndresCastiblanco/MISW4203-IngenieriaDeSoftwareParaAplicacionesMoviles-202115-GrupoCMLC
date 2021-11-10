package com.uniandes.vinilos.model

data class Collector(
    var id: String,
    var name: String,
    var telephone: String,
    var email: String,
    var favoritePerformers: List<CollectorPerformers>,
    var collectorAlbums: List<CollectorAlbums>,
)



