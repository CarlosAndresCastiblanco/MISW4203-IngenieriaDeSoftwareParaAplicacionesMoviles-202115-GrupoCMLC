package com.uniandes.vinilos.di

import com.uniandes.vinilos.provider.AlbumsProvider
import com.uniandes.vinilos.repository.AlbumsRepository
import com.uniandes.vinilos.repository.AlbumsRepositoryImp
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
}








