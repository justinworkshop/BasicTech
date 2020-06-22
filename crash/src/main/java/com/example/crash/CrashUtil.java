package com.example.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2020
 * FileName: CrashUtil
 * Author: zhengwei
 * Date: 2020-06-22 11:02
 * Description:
 */
public class CrashUtil {

    public static Map<String, String> collectDeviceInfo(Context context) {
        Map<String, String> paramsMap = new HashMap<>();

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";

                paramsMap.put("versionName", versionName);
                paramsMap.put("versionCode", versionCode);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // 获取所有系统信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                paramsMap.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        return paramsMap;
    }

    public static void saveCrashInfo2File(Throwable throwable, Map<String, String> paramsMap) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();

        String result = writer.toString();
        sb.append(result);

        FileOutputStream fos = null;
        try {
            File file = new File("");
            fos = new FileOutputStream(file);
            fos.write(result.getBytes());
        } catch (IOException e) {

        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {

            }
        }
    }

}
