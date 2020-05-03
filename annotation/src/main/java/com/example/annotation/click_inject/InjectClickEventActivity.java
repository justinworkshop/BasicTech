package com.example.annotation.click_inject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.R;
import com.example.annotation.click_inject.InjectUtils;
import com.example.annotation.click_inject.annotation.OnClick;
import com.example.annotation.click_inject.annotation.OnLongClick;

/**
 * Copyright (C), 2016-2020
 * FileName: InjectClickEventActivity
 * Author: zhengwei
 * Date: 2020-05-03 18:07
 * Description: 点击事件注入测试页面
 */
public class InjectClickEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_inject);

        InjectUtils.injectEvent(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void click(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.btn1:
                text = "Button 1 click";
                break;
            case R.id.btn2:
                text = "Button 2 click";
                break;
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn1, R.id.btn2})
    public boolean longClick(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.btn1:
                text = "Button 1 long click";
                break;
            case R.id.btn2:
                text = "Button 2 long click";
                break;
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        return true;
    }
}
