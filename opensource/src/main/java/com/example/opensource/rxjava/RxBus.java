package com.example.opensource.rxjava;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Copyright (C), 2016-2020
 * FileName: RxBus
 * Author: zhengwei
 * Date: 2020-05-18 17:36
 * Description: RxBus 组件通信
 */
public class RxBus {
    private final Subject<Object> bus;
    private Disposable disposable;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final RxBus instance = new RxBus();
    }

    public void post(Object object) {
        bus.onNext(object);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return bus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

    public void subscribe(Class clz, Consumer consumer) {
        disposable = toObservable(clz).subscribe(consumer);
    }

    public void unSubcribe() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
