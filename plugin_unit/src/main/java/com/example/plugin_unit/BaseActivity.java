package com.example.plugin_unit;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

/**
 * Copyright (C), 2016-2020
 * FileName: BaseActivity
 * Author: zhengwei
 * Date: 2020-05-25 11:50
 * Description: BaseActivity
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resource = LoadUtils.getResource(getApplication());

        // 替换这个 context 的resource 为我们自己写的 resource
        mContext = new ContextThemeWrapper(getBaseContext(), 0);

        Class<? extends Context> clazz = mContext.getClass();
        try {
            Field mResourcesField = clazz.getDeclaredField("mResources");
            mResourcesField.setAccessible(true);
            mResourcesField.set(mContext, resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
