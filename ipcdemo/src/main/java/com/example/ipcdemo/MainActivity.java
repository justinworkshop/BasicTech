package com.example.ipcdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "IPCClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "当前进程：" + ProcessUtil.getProcessName(MainActivity.this, Process.myPid()));
        bindService(new Intent(this, RemoteService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private IBinder client = new Binder();
    private IMessageSender messageSender;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected");
            messageSender = IMessageSender.Stub.asInterface(iBinder);
            try {
                messageSender.asBinder().linkToDeath(new ClientDeathRecipient(), 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                messageSender.setBinder(client);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected " + componentName);
        }
    };

    class ClientDeathRecipient implements DeathRecipient {

        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied ");
        }
    }
}
