package com.example.hiltdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by zhengwei on 2021/5/23.
 */
@InstallIn(ApplicationComponent::class)
@Module
class HttpModule {
    @Provides
    fun getHttpObject(): HttpObject {
        return HttpObject()
    }
}