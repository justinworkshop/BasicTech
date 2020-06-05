package com.example.opensource.leakcanary;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.opensource.R;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import leakcanary.AppWatcher;

/**
 * Copyright (C), 2016-2020
 * FileName: TestActivity
 * Author: zhengwei
 * Date: 2020-06-05 10:38
 * Description:
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leak_canary_test);

    }

    public void testLeak(View view) {
        Object object = new Object();
        AppWatcher.INSTANCE.getObjectWatcher().watch(object, "object");

        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(object, referenceQueue);
        System.out.println("weakReference: " + weakReference);

        System.out.println("==================");

        object = null;

        Runtime.getRuntime().gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while (referenceQueue != null) {
            Reference ref = referenceQueue.poll();
            if (ref != null) {
                System.out.println(ref.get());
            }
        }
    }
}
