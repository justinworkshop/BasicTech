package com.example.jetpack.lefecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * Copyright (C), 2016-2020
 * FileName: BasePresenter
 * Author: zhengwei
 * Date: 2020-05-08 21:24
 * Description: BasePresenter
 */
public class BasePresenter implements LifecycleIPresenter {
    private static final String TAG = "BasePresenter";

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.d(TAG, "onCreate: " + owner.getClass());
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        Log.d(TAG, "onLifecycleChanged: " + owner.getClass() + ", event: " + event);
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        Log.d(TAG, "onDestroy: " + owner.getClass());
    }
}
