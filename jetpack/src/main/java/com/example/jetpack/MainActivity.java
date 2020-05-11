package com.example.jetpack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpack.lefecycle.LifecycleIPresenter;
import com.example.jetpack.lefecycle.MainPresenter;

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
        threadPool.execute(runnable);


    }
}
