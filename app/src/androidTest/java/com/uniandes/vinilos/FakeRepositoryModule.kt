package com.uniandes.vinilos

import com.uniandes.vinilos.di.RepositoryModule
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Performer
import com.uniandes.vinilos.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun providerAlbumsRepository(): AlbumsRepository =
        object : AlbumsRepository {
            val news = arrayListOf(
                Album(
                    "1", "Album 1", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "2021", "rocl", "EMI", "Album description", arrayListOf(
                        Performer("1", "Artista")
                    )                 ),
                Album(
                    "2", "Album 2", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "2021", "rocl", "EMI", "Album description",  arrayListOf(
                        Performer("1", "Artista")
                    )               ),
            )

            override suspend fun getAlbums(): List<Album> = news

            override suspend fun getAlbum(id: String): Album = news[0]
        }
}