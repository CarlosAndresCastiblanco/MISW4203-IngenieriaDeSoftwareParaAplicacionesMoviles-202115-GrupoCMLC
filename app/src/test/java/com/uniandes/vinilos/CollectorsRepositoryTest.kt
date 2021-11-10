package com.uniandes.vinilos

import com.uniandes.vinilos.provider.CollectorsProvider
import com.uniandes.vinilos.repository.AlbumsRepositoryImp
import com.uniandes.vinilos.repository.CollectorsRepositoryImp
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

class CollectorsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val collectorsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CollectorsProvider::class.java)

    private val collectorsRepository = CollectorsRepositoryImp(collectorsProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get collectors response is correct`() {
        mockWebServer.enqueueResponse("collectors_response.json")

        runBlocking {
            val collectors = collectorsRepository.getCollectors()
            assertEquals(2, collectors.size)
            assertEquals("100", collectors[0].id)
            assertEquals("101", collectors[1].id)
        }
    }


}

private fun MockWebServer.enqueueResponse(filePath: String) {
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










