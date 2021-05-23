package com.example.dagger2demo.scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Scope;

/**
 * Created by zhengwei on 2021/5/23.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface AppScope {

}
