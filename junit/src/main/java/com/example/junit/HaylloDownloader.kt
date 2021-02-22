package com.example.junit

/**
 * Created by zhengwei on 2021/2/21.
 */
open class HaylloDownloader {

    interface IHaylloDownloaderCallback {
        fun onSuccess()
        fun onFailed()
        fun onProgress()
    }

    fun download(url: String, callback: IHaylloDownloaderCallback) {
        callback?.onFailed()
    }

    public fun getUrl(): String {
        return "test"
    }
}