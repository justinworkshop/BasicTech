package com.example.junit;

import com.example.junit.Downloader.IDownloaderCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Created by zhengwei on 2021/2/21.
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloaderTest {

    @Mock
    Downloader downloader;

    @Before
    public void setUp() {

    }

    @Test
    public void download() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                for (Object argument : arguments) {
                    System.out.println("argument: " + argument);
                }
                IDownloaderCallback callback = (IDownloaderCallback) arguments[1];
                callback.onSuccess();
                return null;
            }
        }).when(downloader).download(Mockito.any(String.class), Mockito.any(IDownloaderCallback.class));
        downloader.download("https://abc.com", new IDownloaderCallback() {
            @Override
            public void onSuccess() {
                System.out.println("onSuccess");
            }

            @Override
            public void onFailed() {
                System.out.println("onFailed");
            }
        });
    }

    @Test
    public void getUrl() {
        Mockito.doReturn("https://test.adc.vip").when(downloader).getUrl();
        String url = downloader.getUrl();
        System.out.println("Url: " + url);
    }
}