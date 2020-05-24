package com.example.plugin_host;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "plugin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privateClassLoader();
            }
        });

    }

    private void privateClassLoader() {
        ClassLoader classLoader = getClassLoader();

        while (classLoader != null) {
            Log.e(TAG, "privateClassLoader: " + classLoader);
            classLoader = classLoader.getParent();
        }

        // BootClassLoader
        Log.e(TAG, "privateClassLoader: activity: " + Activity.class.getClassLoader());
        // PathClassLoader
        Log.e(TAG, "privateClassLoader: AppCompatActivity: " + AppCompatActivity.class.getClassLoader());
    }
}
