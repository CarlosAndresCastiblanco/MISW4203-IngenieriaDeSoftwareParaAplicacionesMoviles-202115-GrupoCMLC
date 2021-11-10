package com.uniandes.vinilos.di

import com.uniandes.vinilos.provider.AlbumsProvider
import com.uniandes.vinilos.provider.CollectorsProvider
import com.uniandes.vinilos.repository.AlbumsRepository
import com.uniandes.vinilos.repository.AlbumsRepositoryImp
import com.uniandes.vinilos.repository.CollectorsRepository
import com.uniandes.vinilos.repository.CollectorsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerAlbumsRepository(provider: AlbumsProvider): AlbumsRepository =
        AlbumsRepositoryImp(provider)

    @Provides
    @Singleton
    fun providerCollectorsRepository(provider: CollectorsProvider): CollectorsRepository =
        CollectorsRepositoryImp(provider)
}








