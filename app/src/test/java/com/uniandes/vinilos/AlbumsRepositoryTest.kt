package com.uniandes.vinilos

import com.uniandes.vinilos.provider.AlbumsProvider
import com.uniandes.vinilos.repository.AlbumsRepositoryImp
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val albumsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AlbumsProvider::class.java)

    private val albumsRepository = AlbumsRepositoryImp(albumsProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Top headlines response is correct`() {
        mockWebServer.enqueueResponse("albums_response.json")

        runBlocking {
            val albums = albumsRepository.getAlbums()
            assertEquals(4, albums.size)
            assertEquals("100", albums[0].id)
            assertEquals("101", albums[1].id)
            assertEquals("102", albums[2].id)
            assertEquals("103", albums[3].id)

        }
    }


}

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}










