package com.example.basictech.process;

import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * Copyright (C), 2016-2020
 * FileName: BaseApplicationLogic
 * Author: zhengwei
 * Date: 2020-05-11 19:00
 * Description: 具有生命周期的application，具体进程集成此类
 */
public class BaseApplicationLogic {
    protected MaApplication mApplication;

    public BaseApplicationLogic() {
    }

    public void setApplication(@NonNull MaApplication application) {
        mApplication = application;
    }

    public void onCreate() {
    }

    public void onTerminate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
