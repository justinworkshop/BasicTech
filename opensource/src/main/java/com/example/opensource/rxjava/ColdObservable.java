package com.example.opensource.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright (C), 2016-2020
 * FileName: ColdObservable
 * Author: zhengwei
 * Date: 2020-05-14 10:52
 * Description: 测试Rxjava
 */
public class ColdObservable {
    private static final String TAG = "ColdObservable";

    public static void main(String[] args) {

        // CodeObservable
        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS,
                        Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread())/*.publish()*/;

//        observable.connect();

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("consumer 1: " + aLong);
            }
        });

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("consumer 2: " + aLong);
            }
        });

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("consumer 3: " + aLong);
            }
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
