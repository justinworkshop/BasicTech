package com.example.mvvm.model;

import java.util.Random;

/**
 * Created by zhengwei on 2021/5/17.
 */
public class AccountModel {

    public void queryLevel(String userInput, final Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    callback.onFailed(e.getMessage());
                }

                if (callback != null) {
                    callback.onSuccess(new Random().nextInt(100) + "");
                }
            }
        }).start();

    }

    public interface Callback {

        void onSuccess(String level);

        void onFailed(String msg);
    }

}
