package com.example.dagger2demo.di;

import com.example.dagger2demo.scope.AppScope;
import com.example.dagger2demo.scope.UserScope;
import dagger.Component;

/**
 * Created by zhengwei on 2021/5/23.
 */
@UserScope
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {

    // 当多个Component注册同一个类时，不能使用如下做法；应该使用dependencies
//    void inject(MainActivity activity);

    Presenter providerPresenter();
}
