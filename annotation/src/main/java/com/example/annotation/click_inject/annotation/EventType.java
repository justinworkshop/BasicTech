package com.example.annotation.click_inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2016-2020
 * FileName: EventType
 * Author: zhengwei
 * Date: 2020-05-03 02:55
 * Description: 事件类型
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    Class listenerType();

    String listenerSetter();
}
