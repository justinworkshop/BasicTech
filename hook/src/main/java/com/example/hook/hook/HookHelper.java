package com.example.hook.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.hook.StubActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Copyright (C), 2016-2020
 * FileName: HookHelper
 * Author: zhengwei
 * Date: 2020-05-23 10:59
 * Description: Hook工具类
 */
public class HookHelper {
    private static final String TAG = "HookDemo";
    public static final String EXTRA_TARGET_INTENT = "extra_target_intent";

    /**
     * 反射修改IActivityManager#startActivity()方法的intent参数，目的是通过安全检查
     * <p>
     * 1.找到Hook点，目的是反射修改ActivityManagerNative#Singleton#IActivityManager(8.0以下版本)
     * 2.获取IActivityManager的原始对象
     * 3.动态代理修改startActivity方法的intent参数, 返回代理对象IActivityManagerProxy
     * 4.反射重置修改后的IActivityManager给gDefault变量
     */
    public static void hookIActivityManager() {
        try {
            Field gDefaultField = null;

            // 8.0以上版本，包括8.0 AM.getService() -> IActivityManagerSingleton
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Class<?> activityManager = Class.forName("android.app.ActivityManager");
                gDefaultField = activityManager.getDeclaredField("IActivityManagerSingleton");
            } else {
                // 8.0以下版本 AMN.getDefault() -> gDefault
                Class<?> activityManager = Class.forName("android.app.ActivityManagerNative");
                gDefaultField = activityManager.getDeclaredField("gDefault");
            }

            gDefaultField.setAccessible(true);
            // 1.获取Singleton<IActivityManager>, 用于后面获取IActivityManager对象，即步骤2
            Object gDefault = gDefaultField.get(null);

            // 拿到Singleton的Class对象
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);

            // 2.获取ActivityManagerNative里面的gDefault对象里面的原始的IActivityManager对象
            final Object rawIActivityManager = mInstanceField.get(gDefault);

            // 需要改变IActivityManager#startActivity(....) 方法中的intent参数，用动态代理的方式实现
            Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");

            // 3.动态代理 -> 改变startActivity方法的intent参数(不直接跳到未注册的target，而是先跳到已经注册的stub)
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{iActivityManagerInterface}, new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                    // 只需要针对startActivity方法处理
                    if ("startActivity".equals(method.getName())) {
                        Log.i(TAG, "invoke: 准备启动activity, 把Target intent 替换为 Stub intent，绕过为注册检查");

                        Intent rawIntent = null;
                        int intendArgIndex = 0;

                        // 标识出intent参数的索引
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent) {
                                rawIntent = (Intent) args[i];
                                intendArgIndex = i;
                                break;
                            }
                        }

                        Log.i(TAG, "invoke: rawIntent = " + rawIntent);

                        Intent stubIntent = new Intent();
                        String stubPackage = "com.example.hook";
                        stubIntent.setComponent(new ComponentName(stubPackage, StubActivity.class.getName()));
                        stubIntent.putExtra(EXTRA_TARGET_INTENT, rawIntent);
                        args[intendArgIndex] = stubIntent;

                        Log.i(TAG, "invoke: stub intent = " + stubIntent);
                    }
                    return method.invoke(rawIActivityManager, args);
                }
            });

            // 4.把代理对象融入到framework
            mInstanceField.set(gDefault, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ActivityThread#mH handler中，把target的intent在还原，达到绕过注册检查，也能跳转的目的
     * 我们app所有的activity的生命周期的处理都在mH的handleMessage里面处理
     * 在Android 8.0之前，不同的生命周期对应不同的msg.what处理
     * 在Android 8.0 改成了全部由EXECUTE_TRANSACTION来处理
     */
    public static void hookHandler() {
        try {
            // 获取ActivityThread#sCurrentActivityThread对象
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object sCurrentActivityThread = sCurrentActivityThreadField.get(null);

            // 通过method调用也可以得到sCurrentActivityThread对象
            // Object sCurrentActivityThread = activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null);

            // 获取ActivityThread#Handler
            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            final Handler mH = (Handler) mHField.get(sCurrentActivityThread);

            // 获取mCallback
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);

            mCallbackField.set(mH, new Handler.Callback() {

                @Override
                public boolean handleMessage(Message msg) {
                    Log.i(TAG, "handleMessage: " + msg.what);
                    switch (msg.what) {
                        // 8.0以下版本
                        case 100: {
                            try {
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                Intent intent = (Intent) intentField.get(msg.obj);
                                Intent targetIntent = intent.getParcelableExtra(EXTRA_TARGET_INTENT);
                                intent.setComponent(targetIntent.getComponent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        break;

                        // 8.0以上版本，包括8.0
                        case 159: {
                            Object obj = msg.obj;
                            Log.i(TAG, "handleMessage: obj=" + obj);
                            try {
                                Field mActivityCallbacksField = obj.getClass().getDeclaredField("mActivityCallbacks");
                                mActivityCallbacksField.setAccessible(true);
                                List mActivityCallbacks = (List) mActivityCallbacksField.get(obj);
                                Log.i(TAG, "handleMessage: mActivityCallbacks= " + mActivityCallbacks);

                                if (mActivityCallbacks.size() > 0) {
                                    Log.i(TAG, "handleMessage: size= " + mActivityCallbacks.size());
                                    String className = "android.app.servertransaction.LaunchActivityItem";

                                    if (mActivityCallbacks.get(0).getClass().getCanonicalName().equals(className)) {
                                        Object object = mActivityCallbacks.get(0);
                                        Field intentField = object.getClass().getDeclaredField("mIntent");
                                        intentField.setAccessible(true);
                                        Intent intent = (Intent) intentField.get(object);
                                        Intent targetIntent = intent.getParcelableExtra(EXTRA_TARGET_INTENT);
                                        intent.setComponent(targetIntent.getComponent());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        break;
                    }
                    mH.handleMessage(msg);
                    return true;
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "hookHandler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
