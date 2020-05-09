package com.example.jetpack.databinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.jetpack.R;

/**
 * Copyright (C), 2016-2020
 * FileName: TestDataBindingActivity
 * Author: zhengwei
 * Date: 2020-05-09 09:04
 * Description: 测试dataBinding
 */
public class TestDataBindingActivity extends AppCompatActivity {
    private ActivityTestDatabindingBinding testDBinding;
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testDBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_databinding);
        testDBinding.setLifecycleOwner(this);
        testViewModel = new TestViewModel();
        testDBinding.setVm(testViewModel);
    }

}
