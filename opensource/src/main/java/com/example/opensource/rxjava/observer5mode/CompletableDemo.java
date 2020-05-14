package com.example.opensource.rxjava.observer5mode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Copyright (C), 2016-2020
 * FileName: CompletableDemo
 * Author: zhengwei
 * Date: 2020-05-14 17:42
 * Description: Completable它从来不发射数据，只处理 onComplete 和 onError 事件。可以看成是Rx的Runnable
 */
public class CompletableDemo {
    public static void main(String[] args) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Hello Completable");
            }
        }).subscribe();

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                TimeUnit.SECONDS.sleep(1);
                emitter.onComplete();
            }
        }).andThen(Observable.range(1, 10)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
