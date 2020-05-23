package com.example.argorithmlib.designpattern;

/**
 * Copyright (C), 2016-2020
 * FileName: SingletonByThreadLocal
 * Author: zhengwei
 * Date: 2020-05-22 13:23
 * Description: ThreadLocal实现单例
 */
public class SingletonByThreadLocal {
    private static final ThreadLocal<SingletonByThreadLocal> singleton =
            new ThreadLocal<SingletonByThreadLocal>() {
                @Override
                protected SingletonByThreadLocal initialValue() {
                    return new SingletonByThreadLocal();
                }
            };

    private SingletonByThreadLocal() {

    }

    public static SingletonByThreadLocal getInstance() {
        return singleton.get();
    }
}
