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
                    "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description", arrayListOf(
                        Performer("1", "Artista 1")
                    )                 ),
                Album(
                    "2", "Album 2", "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                    "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description",  arrayListOf(
                        Performer("2", "Artista 2")
                    )               ),
            )

            override suspend fun getAlbums(): List<Album> = news

            override suspend fun getAlbum(id: String): Album = news[0]
        }
}