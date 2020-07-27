package com.example.hotfix.hotfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2016-2020
 * FileName: ReflectUtil
 * Author: zhengwei
 * Date: 2020-07-27 23:52
 * Description: 反射工具类
 */
public class ReflectUtil {
    /**
     * Class对象中找指定的Field
     *
     * @param instance
     * @param name
     * @return
     * @throws Exception
     */
    public static Field getField(Object instance, String name) throws Exception {
        Class<?> clz = instance.getClass();
        while (clz != Object.class) {
            try {
                Field field = clz.getDeclaredField(name);
                if (field != null) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                clz = clz.getSuperclass();
            }
        }

        throw new Exception(instance.getClass().getSimpleName() + " have no such field: " + name);
    }

    /**
     * Class对象中找指定的Method
     *
     * @param instance
     * @param methodName
     * @param parameterTypes
     * @return
     * @throws Exception
     */
    public static Method getMethod(Object instance, String methodName, Class<?>... parameterTypes) throws Exception {
        Class<?> clz = instance.getClass();
        while (clz != Object.class) {
            try {
                Method method = clz.getDeclaredMethod(methodName, parameterTypes);
                if (method != null) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                clz = clz.getSuperclass();
            }
        }

        throw new Exception(instance.getClass().getSimpleName() + " have no such method: " + methodName);
    }
}
