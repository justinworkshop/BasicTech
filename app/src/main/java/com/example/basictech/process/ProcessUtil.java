package com.example.basictech.process;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Copyright (C), 2016-2020
 * FileName: ProcessUtil
 * Author: zhengwei
 * Date: 2020-05-11 19:02
 * Description: 进程工具类
 */
public class ProcessUtil {
    public static int getMyProcessId() {
        return android.os.Process.myPid();
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }
}
