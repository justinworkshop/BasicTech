package com.example.annotation.click_inject.annotation;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2016-2020
 * FileName: OnClick
 * Author: zhengwei
 * Date: 2020-05-03 02:57
 * Description: 点击事件
 */
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    @IdRes int[] value();
}
