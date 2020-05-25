package com.example.plugin_host;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright (C), 2016-2020
 * FileName: ProxyActivity
 * Author: zhengwei
 * Date: 2020-05-25 09:40
 * Description: 代理activity
 */
public class ProxyActivity extends AppCompatActivity {

    private static final String TAG = "plugin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        Log.e(TAG, "onCreate: 我是代理activity");

    }
}
