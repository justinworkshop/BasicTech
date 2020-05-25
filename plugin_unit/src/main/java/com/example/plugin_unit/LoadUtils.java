package com.example.plugin_unit;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

/**
 * Copyright (C), 2016-2020
 * FileName: LoadUtils
 * Author: zhengwei
 * Date: 2020-05-25 11:46
 * Description: 资源加载工具类
 */
public class LoadUtils {
    private final static String apkPath = "/sdcard/plugin_unit-debug.apk";
    private static Resources mResources;

    public static Resources getResource(Context context) {
        if (mResources == null) {
            mResources = loadResource(context);
        }

        return mResources;
    }

    private static Resources loadResource(Context context) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();

            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);

            // 参数就是插件的资源路径
            addAssetPathMethod.invoke(assetManager, apkPath);
            Resources resources = context.getResources();

            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
