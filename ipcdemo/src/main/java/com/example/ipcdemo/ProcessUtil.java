package com.example.ipcdemo;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.List;

/**
 * Created by zhengwei on 2021/9/8.
 */
public class ProcessUtil {

    private static final String TAG = "IPCUtil";

    public static String getProcessName(@NonNull Context context, int pid) {
        final String DEFAULT_PROCESS_NAME = "";
        try {
            Context app = context.getApplicationContext();
            if (app == null) {
                app = context;
            }
            ActivityManager mActivityManager = (ActivityManager) app
                    .getSystemService(Context.ACTIVITY_SERVICE);
            if (mActivityManager == null) {
                Log.e(TAG, "ActivityManager got null!");
                return app.getApplicationInfo().packageName;
            }
            List<RunningAppProcessInfo> infoList =
                    mActivityManager.getRunningAppProcesses();
            if (infoList == null) {
                Log.e(TAG, "getRunningAppProcesses got null!");
                return app.getApplicationInfo().packageName;
            }
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                    .getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    if (appProcess.processName == null || appProcess.processName.isEmpty()) {
                        return app.getApplicationInfo().packageName;
                    } else {
                        return appProcess.processName;
                    }
                }
            }
            return app.getApplicationInfo().packageName;
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
        return DEFAULT_PROCESS_NAME;
    }
}
