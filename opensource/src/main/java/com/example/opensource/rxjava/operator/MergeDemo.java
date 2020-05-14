package com.example.opensource.rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Copyright (C), 2016-2020
 * FileName: MergeDemo
 * Author: zhengwei
 * Date: 2020-05-15 01:32
 * Description: 合并-merge
 */
public class MergeDemo {
    static String result = "结果：";

    public static void main(String[] args) {

        Observable<String> netWork = Observable.just("网络");

        Observable<String> file = Observable.just("本地文件");

        /**
         * zip合并
         */
        Observable.zip(netWork, file, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        /**
         * merge合并，同时发送事件onComplete
         */
        Observable.merge(netWork, file)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        result += s;
                        System.out.println("merge result: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete: " + result);
                    }
                });
    }
}
