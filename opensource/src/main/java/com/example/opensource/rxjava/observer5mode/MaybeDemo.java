package com.example.opensource.rxjava.observer5mode;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Copyright (C), 2016-2020
 * FileName: MaybeDemo
 * Author: zhengwei
 * Date: 2020-05-14 18:03
 * Description: 能发射0个或1个数据，要么成功，要么失败
 */
public class MaybeDemo {
    public static void main(String[] args) {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onComplete();// 发射了onComplete事件，后面事件就不会发射
                emitter.onSuccess("test maybe");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
