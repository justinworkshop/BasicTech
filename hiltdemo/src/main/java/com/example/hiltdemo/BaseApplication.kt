package com.example.hiltdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by zhengwei on 2021/5/23.
 */
@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var hiltTest: HiltTest

    override fun onCreate() {
        super.onCreate()
        hiltTest.hiltTest()
    }
}