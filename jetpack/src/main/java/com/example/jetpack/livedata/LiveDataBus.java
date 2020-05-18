package com.example.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2016-2020
 * FileName: LiveDataBus
 * Author: zhengwei
 * Date: 2020-05-18 17:07
 * Description: LiveDataBus 组件之间通信
 */
public class LiveDataBus {
    private Map<String, MutableLiveData<Object>> bus;

    private LiveDataBus() {
        bus = new ConcurrentHashMap<>();
    }

    public static LiveDataBus getInstance() {
        return HOLDER.instance;
    }

    private static class HOLDER {
        private static LiveDataBus instance = new LiveDataBus();
    }


    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }
}
