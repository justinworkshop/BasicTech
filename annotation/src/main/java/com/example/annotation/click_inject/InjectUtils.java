package com.example.annotation.click_inject;

import android.app.Activity;
import android.view.View;

import com.example.annotation.click_inject.annotation.EventType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2016-2020
 * FileName: InjectUtils
 * Author: zhengwei
 * Date: 2020-05-03 10:36
 * Description: 注入辅助类
 */
public class InjectUtils {
    public static void injectEvent(final Activity target) {
        if (target == null) {
            return;
        }

        Class<? extends Activity> clazz = target.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            // 获取方法上所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // 注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    // 获取元注解中定义的值
                    Class listenerType = eventType.listenerType();
                    String listenerSetter = eventType.listenerSetter();

                    try {
                        // 获取OnClick/OnLongClick中定义的id
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] ids = (int[]) valueMethod.invoke(annotation);

                        method.setAccessible(true);
                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(method, target);
                        Object listenerProxy = Proxy.newProxyInstance(target.getClassLoader(), new Class[]{listenerType}, listenerInvocationHandler);
                        for (int id : ids) {
                            View view = target.findViewById(id);
                            // 反射获取方法:setOnClickListener/setOnLongClickListener
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            // 动态代理执行方法:setOnClickListener/setOnLongClickListener
                            setter.invoke(view, listenerProxy);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(Method method, T target) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return this.method.invoke(target, objects);
        }
    }
}
