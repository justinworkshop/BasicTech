package com.example.crash;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 * Copyright (C), 2016-2020
 * FileName: MyExceptionHandler
 * Author: zhengwei
 * Date: 2020-06-22 10:53
 * Description:
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置MyExceptionHandler为系统默认的
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            // 未处理的交给系统处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            Intent intent = new Intent(mContext, CrashDisplayActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    /**
     * 自己处理异常
     *
     * @param throwable
     * @return
     */
    private boolean handleException(Throwable throwable) {
        boolean result = false;

        if (throwable instanceof Exception) {
            result = true;

            Map<String, String> map = CrashUtil.collectDeviceInfo(mContext);
            CrashUtil.saveCrashInfo2File(throwable, map);
        }

        return result;
    }

}
