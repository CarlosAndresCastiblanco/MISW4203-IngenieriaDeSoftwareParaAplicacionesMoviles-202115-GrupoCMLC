package com.uniandes.vinilos.di

import com.uniandes.vinilos.provider.AlbumsProvider
import com.uniandes.vinilos.provider.ArtistsProvider
import com.uniandes.vinilos.provider.CollectorsProvider
import com.uniandes.vinilos.repository.*
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

    @Provides
    @Singleton
    fun providerArtistsRepository(provider: ArtistsProvider): ArtistsRepository =
        ArtistsRepositoryImp(provider)
}








