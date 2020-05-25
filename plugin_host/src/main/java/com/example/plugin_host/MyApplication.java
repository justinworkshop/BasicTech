package com.example.plugin_host;

import android.app.Application;

/**
 * Copyright (C), 2016-2020
 * FileName: MyApplication
 * Author: zhengwei
 * Date: 2020-05-25 09:09
 * Description: application
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LoadUtil.loadClass(this);
        HookUtil.hookAMS();
        HookUtil.hookHandler();
    }
}