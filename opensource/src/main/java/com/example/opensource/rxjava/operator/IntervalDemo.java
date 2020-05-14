package com.example.opensource.rxjava.operator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright (C), 2016-2020
 * FileName: IntervalDemo
 * Author: zhengwei
 * Date: 2020-05-15 01:20
 * Description: 创建-interval
 */
public class IntervalDemo {
    public static void main(String[] args) {
        interval();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void interval() {
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(4)
                .observeOn(Schedulers.trampoline())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
