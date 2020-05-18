package com.example.jetpack;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.jetpack.lefecycle.LifecycleIPresenter;
import com.example.jetpack.lefecycle.MainPresenter;
import com.example.jetpack.livedata.LiveDataBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private LifecycleIPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        getLifecycle().addObserver(presenter);
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
