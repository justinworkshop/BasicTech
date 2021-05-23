package com.example.dagger2demo;

import android.app.Application;
import com.example.dagger2demo.component.DaggerMyComponent;
import com.example.dagger2demo.component.MyComponent;
import com.example.dagger2demo.module.DatabaseModule;
import com.example.dagger2demo.module.HttpModule;

/**
 * Created by zhengwei on 2021/5/23.
 */
public class BaseApplication extends Application {

    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myComponent = DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .build();
    }

    public MyComponent getAppMyComponent() {
        return myComponent;
    }
}
