package com.example.hook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hook.hook.HookHelper;

/**
 * Hook调用未注册的Activity
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HookDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTarget(View view) {
        Log.i(TAG, "startTarget  SDK INT: " + Build.VERSION.SDK_INT);
        HookHelper.hookIActivityManager();
        HookHelper.hookHandler();

        // 通过adb shell dumpsys activity activities|grep "Run"
        // 查看Activity任务栈，得到的结果是AndroidManifest.xml中注册的activity
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }
}
