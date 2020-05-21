package com.example.opensource.mmkv;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

/**
 * Copyright (C), 2016-2020
 * FileName: MMKVActivity
 * Author: zhengwei
 * Date: 2020-05-21 10:21
 * Description: 学习MMKV
 */
public class MMKVActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String initialize = MMKV.initialize(this);
        System.out.println(initialize+", pageSize: " +MMKV.pageSize());

        MMKV mmkv = MMKV.mmkvWithAshmemFD("id", MMKV.MULTI_PROCESS_MODE)
        mmkv.encode("int", 111);
        mmkv.putBoolean()


    }

    @Override
    protected void onResume() {
        super.onResume();

        MMKV mmkv = MMKV.defaultMMKV();
        System.out.println("mmkv:"+mmkv.decodeInt("int"));
    }
}
