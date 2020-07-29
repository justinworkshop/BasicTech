package com.example.basictech;

import android.os.SystemClock;

import java.util.Date;

/**
 * Created by zhengwei on 2020/7/24.
 */
public class TestClass {
    public static void main(String[] args) {
        System.out.println(SystemClock.currentThreadTimeMillis());
        System.out.println(System.currentTimeMillis());
        Date date = new Date();
        System.out.println(date.getTime());
    }
}
