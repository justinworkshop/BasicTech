package com.example.dagger2demo.module;

import com.example.dagger2demo.object.DatabaseObject;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zhengwei on 2021/5/23.
 */
@Module
public class DatabaseModule {

    @Provides
    public DatabaseObject providerDatabaseObject() {
        return new DatabaseObject();
    }

}
