package com.example.hiltdemo

import android.util.Log
import javax.inject.Inject

/**
 * Created by zhengwei on 2021/5/23.
 */
private const val TAG = "HiltTest"

class HiltTest @Inject constructor() {

    fun hiltTest() {
        Log.e(TAG, "hiltTest")
    }
}