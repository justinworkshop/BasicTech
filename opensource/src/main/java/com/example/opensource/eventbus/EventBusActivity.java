package com.example.opensource.eventbus;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (C), 2016-2020
 * FileName: EventBusActivity
 * Author: zhengwei
 * Date: 2020-05-18 12:40
 * Description: EventBus测试
 */
public class EventBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void handleEvent(MsgEvent event) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().post(new MsgEvent());
        EventBus.getDefault().postSticky(new MsgEvent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
