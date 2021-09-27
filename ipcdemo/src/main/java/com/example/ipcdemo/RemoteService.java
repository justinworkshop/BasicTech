package com.example.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;

/**
 * Created by zhengwei on 2021/9/8.
 */
public class RemoteService extends Service {

    private static final String TAG = "IPCServer";
    private Handler serverHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "当前进程：" + ProcessUtil.getProcessName(RemoteService.this, Process.myPid()));

        serverHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "准备crash：");

                int a = 1 / 0;
            }
        }, 5000L);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IMessageSender.Stub messageSenderStub = new IMessageSender.Stub() {

            @Override
            public void sendMessage(MessageModel model) throws RemoteException {
                Log.i(TAG, "sendMessage: " + model);
            }

            @Override
            public void setBinder(IBinder client) throws RemoteException {
                client.linkToDeath(serviceRecipient, 0);
            }
        };
        return messageSenderStub;
    }


    private IBinder.DeathRecipient serviceRecipient = new DeathRecipient() {
        @Override
        public void binderDied() {
            System.out.println("Client is died.");
        }
    };
}
