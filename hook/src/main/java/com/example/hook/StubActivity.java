package com.example.hook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright (C), 2016-2020
 * FileName: StubActivity
 * Author: zhengwei
 * Date: 2020-05-23 11:22
 * Description: 桩Activity
 */
public class StubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);
    }
}
