package com.example.junit

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by zhengwei on 2021/2/21.
 */
@RunWith(MockitoJUnitRunner::class)
class HaylloDownloaderTest {
    @Mock
    lateinit var downloader: HaylloDownloader

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    fun <T> any(): T = Mockito.any<T>()

    @Test
    fun download() {
//        Mockito.doAnswer { invocation ->
//            val callback = invocation.getArgument<HaylloDownloader.IHaylloDownloaderCallback>(1)
//            callback.onProgress()
//        }.`when`(downloader).download(Mockito.anyString(), any())

        downloader.download("China is a great country", object : HaylloDownloader.IHaylloDownloaderCallback {
            override fun onFailed() {
                println("onFailed")
            }

            override fun onProgress() {
                println("onProgress")
            }

            override fun onSuccess() {
                println("onSuccess")
            }
        })
    }

    @Test
    fun getUrl() {
//        Mockito.doReturn("Hello china").`when`(downloader).getUrl()
        val downloader = Mockito.mock(HaylloDownloader::class.java)
        Mockito.`when`(downloader.getUrl()).thenReturn("Hello china")
        println("Url: " + downloader.getUrl())
    }
}