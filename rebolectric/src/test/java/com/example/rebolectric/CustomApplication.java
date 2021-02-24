package com.example.rebolectric;

import android.app.Application;

/**
 * Created by zhengwei on 2021/2/24.
 */
public class CustomApplication extends Application {

    /**
     * 由于我们的Local是直接在PC上运行的，所以调用这些系统API便会出错。
     * 那么问题来了，我们既要使用Local测试，但测试过程又难免遇到调用系统API那怎么办？
     * 其中一个方法就是mock objects，比如借助Mockito，另外一种方式就是使用Robolectric
     *
     * Robolectric就是为解决这个问题而生的。
     * 它实现一套JVM能运行的Android代码，然后在unit test运行的时候去截取android相关的代码调用，然后转到他们的他们实现的Shadow代码去执行这个调用的过程
     */

}
