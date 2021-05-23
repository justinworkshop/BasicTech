package com.example.dagger2demo.di;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhengwei on 2021/5/23.
 */
@Module
public class PresenterModule {

    @Provides
    public Presenter providerPresenter() {
        return new Presenter();
    }

}
