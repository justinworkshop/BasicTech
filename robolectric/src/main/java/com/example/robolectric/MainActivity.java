package com.example.robolectric;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * Robolectric测试要点
     * https://juejin.cn/post/6844904001960411149#heading-24
     *
     * 页面跳转
     * UI组件状态
     * 验证Dialog
     * 验证Fragment
     * 验证Toast
     * 验证BroadcastReceiver
     * 验证Service
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                intent.putExtra("key", 111);
                MainActivity.this.startActivity(intent);
            }
        });

        registerBroadcast();
    }

    private void registerBroadcast() {
        String action = "com.robolectric.Test";
        IntentFilter filter = new IntentFilter();
        filter.addAction(action);
        this.registerReceiver(new MyBroadcastReceiver(), filter);
    }
}
