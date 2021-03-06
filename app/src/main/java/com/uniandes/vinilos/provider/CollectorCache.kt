package com.uniandes.vinilos.provider

import com.uniandes.vinilos.model.Collector

object CollectorCache {
    private var collectors:List<Collector> = listOf()

    fun getCollector() = this.collectors
    fun setCollectors(value:List<Collector>) {
        this.collectors = value
    }

}