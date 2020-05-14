package com.example.opensource.rxjava.observer5mode;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Copyright (C), 2016-2020
 * FileName: ObservableDemo
 * Author: zhengwei
 * Date: 2020-05-14 18:09
 * Description: 发射0或者n个事件，并且以成功或错误事件终止
 */
public class ObservableDemo {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Test observable");
                emitter.onNext("end test");
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
