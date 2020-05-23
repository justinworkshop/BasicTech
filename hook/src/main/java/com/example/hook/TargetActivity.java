package com.example.hook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright (C), 2016-2020
 * FileName: TargetActivity
 * Author: zhengwei
 * Date: 2020-05-23 11:20
 * Description: 插件activity
 */
public class TargetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
    }
}
