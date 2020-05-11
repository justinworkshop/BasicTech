package com.example.basictech;

import android.app.Application;

import com.example.basictech.process.ProcessUtil;

/**
 * Copyright (C), 2016-2020
 * FileName: MainApplication
 * Author: zhengwei
 * Date: 2020-05-11 18:48
 * Description: application
 * 这里演示两种多进程初始化方式：
 * 1. 使用进程名过滤，分之处理各个进程的初始化
 * 2. 创建多个感知生命周期的application，各个进程在对应的application做初始化工作
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = ProcessUtil.getProcessName(this, ProcessUtil.getMyProcessId());
        if (processName != null) {
            boolean defaultProcess = processName.equals(Constants.REAL_PACKAGE_NAME);
            // 默认的主进程启动时初始化应用
            if (defaultProcess) {
                initAppForMainProcess();
            }
            // 其他进程启动时初始化对应内容
            else if (processName.contains(":webbrowser")) {

            } else if (processName.contains(":bgmusic")) {

            }
        }
    }


    private void initAppForMainProcess() {

    }
}
