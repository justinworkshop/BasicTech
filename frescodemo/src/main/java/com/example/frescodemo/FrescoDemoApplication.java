package com.example.frescodemo;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhengwei on 2021/8/21.
 */
public class FrescoDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
