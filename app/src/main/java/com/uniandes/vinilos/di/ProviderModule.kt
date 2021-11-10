package com.uniandes.vinilos.di

import com.uniandes.vinilos.provider.AlbumsProvider
import com.uniandes.vinilos.provider.CollectorsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://vinylsg15.herokuapp.com/".toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun providerAlbumsProvider(retrofit: Retrofit): AlbumsProvider =
        retrofit.create(AlbumsProvider::class.java)

    @Provides
    @Singleton
    fun providerCollectorsProvider(retrofit: Retrofit): CollectorsProvider =
        retrofit.create(CollectorsProvider::class.java)
}








