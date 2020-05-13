package com.example.view;

import android.os.Bundle;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view.defineview.FlowLayout;
import com.example.view.defineview.PieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2020
 * FileName: DefineViewActivity
 * Author: zhengwei
 * Date: 2020-05-13 08:25
 * Description: 自定义view页面
 */
public class DefineViewActivity extends AppCompatActivity {
    private PieView mPieView;
    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_view);
        mPieView = findViewById(R.id.pie_view);
        mPieView.setProgress(70);

        mFlowLayout=findViewById(R.id.flow_layout);
        List<String> data = new ArrayList<>();
        data.add("11111111111111");
        data.add("22");
        data.add("3333333333333");
        data.add("aaaaaaaaaaaaaaaa");
        data.add("bbbbbb");
        data.add("ccccccccc");
        mFlowLayout.setData(data);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 20, 10, 20);
        for(int i=0;i<data.size();i++){
            TextView textView = new TextView(this);
            textView.setText(data.get(i));
            textView.setLayoutParams(params);
            mFlowLayout.addView(textView);
        }
    }
}
