package com.example.annotation.click_inject.annotation;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2016-2020
 * FileName: OnLongClick
 * Author: zhengwei
 * Date: 2020-05-03 03:00
 * Description: 长按点击事件
 */
@EventType(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnLongClick {
    @IdRes int[] value();
}
