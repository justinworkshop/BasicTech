package com.example.annotation.params_inject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.example.annotation.params_inject.annotation.AutoWried;
import com.example.annotation.params_inject.annotation.InjectView;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Copyright (C), 2016-2020
 * FileName: InjectUtils
 * Author: zhengwei
 * Date: 2020-05-03 18:15
 * Description: 注入工具类
 */
public class InjectUtils {

    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();

        // 获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field filed : declaredFields) {
            // 判断属性是否被InjectView注解声明
            if (filed.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = filed.getAnnotation(InjectView.class);
                // 获得了注解中设置的id
                int id = injectView.value();
                View view = activity.findViewById(id);
                // 设置访问权限，允许操作private的属性
                filed.setAccessible(true);
                try {
                    // 反射赋值
                    filed.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void injectAutoWried(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        // 获得数据
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        // 获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(AutoWried.class)) {
                AutoWried autoWried = field.getAnnotation(AutoWried.class);
                // 获得key
                String key = TextUtils.isEmpty(autoWried.value()) ? field.getName() : autoWried.value();

                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);
                    // Parcelable数组类型不能直接设置，其他的都可以

                    // 获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    // 当前属性是数组并且是 Parcelable（子类）数组
                    if (field.getType().isArray() &&
                            Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        // 创建对应类型的数组并由objs拷贝

                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } // end if
        } // end for
    }
}
