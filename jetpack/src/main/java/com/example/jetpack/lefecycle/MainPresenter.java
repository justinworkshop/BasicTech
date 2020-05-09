package com.example.jetpack.lefecycle;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * Copyright (C), 2016-2020
 * FileName: MainPresenter
 * Author: zhengwei
 * Date: 2020-05-08 21:19
 * Description: MainPresenter
 */
public class MainPresenter extends BasePresenter {
    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        super.onCreate(owner);
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        super.onLifecycleChanged(owner, event);
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
    }
}
