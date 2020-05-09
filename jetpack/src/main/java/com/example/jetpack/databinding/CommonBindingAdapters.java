package com.example.jetpack.databinding;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Copyright (C), 2016-2020
 * FileName: CommonBindingAdapters
 * Author: zhengwei
 * Date: 2020-05-09 09:35
 * Description: 自定义BindingAdapter
 */
public class CommonBindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .into(view);
        }
    }


}
