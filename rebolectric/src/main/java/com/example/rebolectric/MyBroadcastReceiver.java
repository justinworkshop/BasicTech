package com.example.rebolectric;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhengwei on 2021/2/24.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    public int flag =  0;

    @Override
    public void onReceive(Context context, Intent intent) {
        flag = 1;
    }
}
