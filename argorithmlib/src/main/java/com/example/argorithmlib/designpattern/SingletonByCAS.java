package com.example.argorithmlib.designpattern;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (C), 2016-2020
 * FileName: SingletonByCAS
 * Author: zhengwei
 * Date: 2020-05-22 13:18
 * Description: CAS 线程安全
 */
public class SingletonByCAS {
    private static final AtomicReference<SingletonByCAS> INSTANCE = new AtomicReference<>();

    private SingletonByCAS() {

    }

    public static SingletonByCAS getInstance() {
        for (; ; ) {
            SingletonByCAS singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }

            singleton = new SingletonByCAS();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
