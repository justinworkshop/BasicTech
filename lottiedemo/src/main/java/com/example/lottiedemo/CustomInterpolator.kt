package com.example.lottiedemo

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator

/**
 * Created by zhengwei on 2021/7/5.
 */
class CustomInterpolator : LinearInterpolator() {
    override fun getInterpolation(input: Float): Float {
        println("input: $input, res: ${(Math.cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.9f}")
        return (Math.cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.1f
    }

}