package com.example.annotation.params_inject.annotation;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2016-2020
 * FileName: InjectView
 * Author: zhengwei
 * Date: 2020-05-03 18:18
 * Description: 自定义注解，标识view
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    @IdRes int value();
}
