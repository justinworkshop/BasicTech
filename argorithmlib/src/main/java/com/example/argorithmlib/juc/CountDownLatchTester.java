package com.example.argorithmlib.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright (C), 2016-2020
 * FileName: CountDownLatchTester
 * Author: zhengwei
 * Date: 2020-05-12 21:53
 * Description: CountDownLatch
 */
public class CountDownLatchTester {
    private CountDownLatch countDownLatch = new CountDownLatch(3);
    private int result;

    public static void main(String[] args) {
        CountDownLatchTester tester = new CountDownLatchTester();
        tester.test();
    }

    public void test() {
        for (int i = 0; i < 3; i++) {
            WorkThread workThread = new WorkThread();
            workThread.start();
        }

        System.out.println(Thread.currentThread().getName() + " - before await");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - after await, result:" + result);
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();

            synchronized (countDownLatch) {
                for (int i = 0; i < 101; i++) {
                    result += i;
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " - " + result + ", countDown:" + countDownLatch.getCount());
            }
        }
    }

}
