package com.example.plugin_host;

import android.app.Activity;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "plugin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                privateClassLoader();

//                loadPluginByClassLoader();

//                loadPluginDexInsert();


                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.plugin_unit", "com.example.plugin_unit.MainActivity"));
                startActivity(intent);
            }
        });

    }

    /**
     * 通过ClassLoader加载dex插件：宿主和插件是两个DexElement
     */
    private void loadPluginByClassLoader() {
        DexClassLoader dexClassLoader = new DexClassLoader("/sdcard/text.dex", MainActivity.this.getCacheDir().getAbsolutePath(), null, MainActivity.this.getClassLoader());

        try {
            Class<?> clazz = dexClassLoader.loadClass("com.example.plugin_unit.Test");
            Method method = clazz.getMethod("print");
            method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Dex插桩加载dex插件：宿主与插件合并为一个DexElement
     */
    private void loadPluginDexInsert() {
//        LoadUtil.loadClass(getApplicationContext());

        try {
            Class<?> clazz = Class.forName("com.example.plugin_unit.Test");
            Method method = clazz.getMethod("print");
            method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void privateClassLoader() {
        ClassLoader classLoader = getClassLoader();

        while (classLoader != null) {
            Log.e(TAG, "privateClassLoader: " + classLoader);
            classLoader = classLoader.getParent();
        }

        // BootClassLoader
        Log.e(TAG, "privateClassLoader: activity: " + Activity.class.getClassLoader());
        // PathClassLoader
        Log.e(TAG, "privateClassLoader: AppCompatActivity: " + AppCompatActivity.class.getClassLoader());
    }
}
