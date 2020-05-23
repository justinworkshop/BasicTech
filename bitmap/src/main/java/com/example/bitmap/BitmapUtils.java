package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Copyright (C), 2016-2020
 * FileName: BitmapUtils
 * Author: zhengwei
 * Date: 2020-05-22 10:23
 * Description: 工具类
 */
public class BitmapUtils {
    public static boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outHeight / Math.max(targetOptions.inSampleSize, 1);
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());

        return byteCount <= candidate.getAllocationByteCount();
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        int bytesPerPixel;
        switch (config) {
            case ALPHA_8:
                bytesPerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytesPerPixel = 2;
                break;
            default:
                bytesPerPixel = 4;
                break;
        }

        return bytesPerPixel;
    }
}
