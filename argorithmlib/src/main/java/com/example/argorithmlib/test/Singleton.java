package com.example.argorithmlib.test;

import java.io.ObjectStreamException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2020
 * FileName: Singleton
 * Author: zhengwei
 * Date: 2020-06-06 23:20
 * Description:
 */
public class Singleton {

    static class Singleton1 {
        private volatile static Singleton1 sInstance;

        private Singleton1() {

        }

        public static Singleton1 getInstance() {
            if (sInstance == null) {// 避免不必要对同步
                synchronized (Singleton.class) {
                    if (sInstance == null) {// 对象为null才创建对象（因为有3个步骤）
                        // 1.为mInstance分配内存 2.调用构造方法，初始化成员字段 3.将mInstance指向分配对内存空间
                        // 这里存在JVM指令重排序问题1-2-3／1-3-2，所以在多线程情况下有可能出现DCL失效问题，解决方法是volatile
                        sInstance = new Singleton1();
                    }
                }
            }

            return sInstance;
        }

        // 防止反序列化创建对象
        private Object readResolve() throws ObjectStreamException {
            return sInstance;
        }
    }


    static class Singleton2 {
        private volatile static Singleton2 sInstance;

        private Singleton2() {

        }

        public static Singleton2 getInstance() {
            return Holder.INSTANCE;
        }

        private static class Holder {
            private static final Singleton2 INSTANCE = new Singleton2();
        }
    }

    /**
     * 默认枚举实例的创建就是线程安全, 任何情况下都是一个单例
     */
    static enum Singleton3 {
        INSTANCE;

        public void doSomething() {
            System.out.println("enum");
        }
    }

    /**
     * context.getSystemService(key)就是这种方式
     */
    static class SingletonManager {
        private static Map<String, Object> objectMap = new HashMap<>();

        private SingletonManager() {

        }

        public static void registerService(String key, Object instance) {
            if (!objectMap.containsKey(key)) {
                objectMap.put(key, instance);
            }
        }

        public static Object getService(String key) {
            return objectMap.get(key);
        }
    }

    public static void main(String[] args) {
        Singleton3.INSTANCE.doSomething();
    }


}
