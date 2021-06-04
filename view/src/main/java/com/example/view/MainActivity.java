package com.example.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        // ScrollView内部嵌套LinearLayout，测试ACTION_CANCEL事件
        // 产生原因是 ScrollView刚开始不拦截，滑动一段距离才拦截，此时子View就会产生Cancel事件
    }
}
