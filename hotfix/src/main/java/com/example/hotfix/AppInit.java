package com.example.hotfix;

import android.app.Application;
import android.content.Context;

import com.example.hotfix.hotfix.HotFix;

import java.io.File;

/**
 * Copyright (C), 2016-2020
 * FileName: AppInit
 * Author: zhengwei
 * Date: 2020-07-28 00:02
 * Description: application
 */
public class AppInit extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        File file = new File("/sdcard/aa/patch.jar");
        HotFix.installPatch(this, file);
        System.out.println("Install patch end");
    }
}
