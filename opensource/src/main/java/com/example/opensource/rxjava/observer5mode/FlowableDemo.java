package com.example.opensource.rxjava.observer5mode;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Copyright (C), 2016-2020
 * FileName: FlowableDemo
 * Author: zhengwei
 * Date: 2020-05-14 18:12
 * Description: 在Observable的基础上，增加了BackPress
 */
public class FlowableDemo {
    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("Test back pressure");
            }
        }, BackpressureStrategy.BUFFER).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s + "---map";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
