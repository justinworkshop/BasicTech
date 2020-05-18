package com.example.opensource.rxjava.observer5mode;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                System.out.println("Observable subscribe called in " + Thread.currentThread().getName());
                emitter.onNext("Test emitter");
                emitter.onComplete();
            }
        }).observeOn(Schedulers.newThread()) // 切换被观察者发射事件的线程
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Observer onSubscribe called in " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext " + s + " in thread: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete ");
                    }
                });


       /* ObservableOnSubscribe observableOnSubscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("Observable Thread: " + Thread.currentThread().getName());

                emitter.onNext("Test observable 2");
                emitter.onNext("end test 2");
                emitter.onComplete();
            }
        };

        ObservableCreate<String> observableCreate = (ObservableCreate<String>) Observable.create(observableOnSubscribe);

        Observable<String> observableSubscribeOn = observableCreate.subscribeOn(Schedulers.io());
        Observable<String> observableObserveOn = observableSubscribeOn.observeOn(Schedulers.io());
        observableObserveOn.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(">> onSubscribe Observer Thread: " + Thread.currentThread().getName());
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println(">>  onNext Observer Thread: " + Thread.currentThread().getName());
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });*/

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
