package com.example.dagger2demo.module;

import com.example.dagger2demo.object.HttpObject;
import com.example.dagger2demo.scope.AppScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by zhengwei on 2021/5/23. 这个类用来提供对象
 */
@AppScope
@Module
public class HttpModule {

    @AppScope
    @Provides
    public HttpObject providerHttpObject() {
        return new HttpObject();
    }

}
