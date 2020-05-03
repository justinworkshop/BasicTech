package com.example.annotation.params_inject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.R;
import com.example.annotation.params_inject.annotation.AutoWried;
import com.example.annotation.params_inject.annotation.InjectView;
import com.example.annotation.params_inject.annotation.UserInfo;

import java.util.Arrays;
import java.util.List;


/**
 * Copyright (C), 2016-2020
 * FileName: ParseParamsActivity
 * Author: zhengwei
 * Date: 2020-05-03 18:12
 * Description: 通过注解解析参数
 */
public class ParseParamsActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    private TextView textView;

    @AutoWried
    private String name;

    @AutoWried("attr")
    String attr;

    @AutoWried
    private int[] array;

    @AutoWried
    private UserInfo userInfo;

    @AutoWried
    private UserInfo[] userInfos;

    private List<UserInfo> userInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_params);

        InjectUtils.injectView(this);
        InjectUtils.injectAutoWried(this);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ParseParamsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "ParseParamsActivity{" +
                "name='" + name + '\'' +
                ", attr='" + attr + '\'' +
                ", array=" + Arrays.toString(array) +
                ", userInfo=" + userInfo +
                ", userInfos=" + Arrays.toString(userInfos) +
                ", userInfoList=" + userInfoList +
                '}';
    }

}
