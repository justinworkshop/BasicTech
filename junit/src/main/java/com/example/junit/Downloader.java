package com.example.junit;

/**
 * Created by zhengwei on 2021/2/21.
 */
public class Downloader {

    public interface IDownloaderCallback {

        void onSuccess();

        void onFailed();
    }

    public void download(String url, IDownloaderCallback callback) {
        callback.onFailed();
    }

    public String getUrl() {
        return "http://test.abc";
    }

}
