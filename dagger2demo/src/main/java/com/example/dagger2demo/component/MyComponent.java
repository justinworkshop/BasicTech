package com.example.dagger2demo.component;

import com.example.dagger2demo.MainActivity;
import com.example.dagger2demo.module.DatabaseModule;
import com.example.dagger2demo.module.HttpModule;
import dagger.Component;

/**
 * Created by zhengwei on 2021/5/23. 组建
 */
@Component(modules = {HttpModule.class, DatabaseModule.class})
public interface MyComponent {
    // 这里的参数是不能使用多态的。
    void injectMainActivity(MainActivity mainActivity);
}
