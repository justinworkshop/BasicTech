package com.example.jetpack;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.jetpack.lefecycle.presenter.LifecycleIPresenter;
import com.example.jetpack.lefecycle.presenter.MainPresenter;
import com.example.jetpack.lefecycle.videoplayer.VideoPlayer;
import com.example.jetpack.livedata.LiveDataBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private LifecycleIPresenter presenter;
    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        videoPlayer = new VideoPlayer();

        getLifecycle().addObserver(presenter);
        getLifecycle().addObserver(videoPlayer);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        threadPool.submit(runnable);

        LiveDataBus.getInstance().with("msg", String.class).postValue("Hello LiveData");

        LiveDataBus.getInstance().with("msg", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("LiveData", "onChanged: " + s);
            }
        });

    }
}
