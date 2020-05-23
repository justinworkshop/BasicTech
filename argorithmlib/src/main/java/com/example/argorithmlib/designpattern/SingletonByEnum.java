package com.example.argorithmlib.designpattern;

/**
 * Copyright (C), 2016-2020
 * FileName: SingletonByEnum
 * Author: zhengwei
 * Date: 2020-05-22 13:28
 * Description: Singleton 通过枚举实现
 */
public enum  SingletonByEnum {
    INSTANCE;

    public static SingletonByEnum getInstance() throws NoSuchMethodException{
        return INSTANCE;
    }

}
