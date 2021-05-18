package com.example.mvvm.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

/**
 * Created by zhengwei on 2021/5/17.
 * 自定义LiveData
 */
public class NetworkLiveData extends LiveData<Network> {
    private final Context context;
    private static NetworkLiveData sNetworkLiveData;
    private NetworkReceiver networkReceiver;
    private IntentFilter intentFilter;

    public NetworkLiveData(Context context) {
        this.context = context;
        networkReceiver = new NetworkReceiver();
        intentFilter = new IntentFilter(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
    }

    public static NetworkLiveData getInstance(Context context) {
        if(sNetworkLiveData == null) {
            sNetworkLiveData = new NetworkLiveData(context);
        }
        return sNetworkLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        context.registerReceiver(networkReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(networkReceiver);
    }

    public static class NetworkReceiver extends BroadcastReceiver {

        @RequiresApi(api = VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network activeNetwork = manager.getActiveNetwork();
            getInstance(context).setValue(activeNetwork);
        }
    }
}
