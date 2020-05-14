package com.example.opensource.rxjava.operator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Copyright (C), 2016-2020
 * FileName: FromIterable
 * Author: zhengwei
 * Date: 2020-05-15 01:45
 * Description: 创建-from
 */
public class FromIterable {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);

        Observable.fromIterable(list)
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
