package com.example.opensource.rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Copyright (C), 2016-2020
 * FileName: FromArray
 * Author: zhengwei
 * Date: 2020-05-15 01:48
 * Description: 创建-from
 */
public class FromArray {
    public static void main(String[] args) {
        Integer[] array = {1,2,3};

        Observable.fromArray(array)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe thread:" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext " + integer);

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError " + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
