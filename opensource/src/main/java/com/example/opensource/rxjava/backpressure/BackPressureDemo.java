package com.example.opensource.rxjava.backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright (C), 2016-2020
 * FileName: BackPressureDemo
 * Author: zhengwei
 * Date: 2020-05-14 21:20
 * Description: 背压
 * RxJava 1.0 Observable支持背压
 * RxJava 2.0 推荐使用Flowable，Observable不再支持背压
 */
public class BackPressureDemo {

    static Subscription subscription;

    public static void main(String[] args) {

        /**
         * 1.背压概念：上有的发送速度快，下游处理速度满
         * 2.控制速度：Subscription
         */


        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                for (int i = 0; ; i++) {
//                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(10);
                    emitter.onNext(1);
                    emitter.onComplete();
//                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                /*.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });*/
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
        ;
    }
}
