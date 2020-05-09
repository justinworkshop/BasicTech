package com.example.jetpack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpack.lefecycle.LifecycleIPresenter;
import com.example.jetpack.lefecycle.MainPresenter;

public class MainActivity extends AppCompatActivity {
    private LifecycleIPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        getLifecycle().addObserver(presenter);
    }
}
