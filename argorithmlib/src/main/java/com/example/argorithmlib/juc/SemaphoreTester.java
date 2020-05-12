package com.example.argorithmlib.juc;

import java.util.concurrent.Semaphore;

/**
 * Copyright (C), 2016-2020
 * FileName: SemaphoreTester
 * Author: zhengwei
 * Date: 2020-05-12 21:44
 * Description: Semaphore
 */
public class SemaphoreTester {
    public static void main(String[] args) {
        SemaphoreTester tester = new SemaphoreTester();
        tester.test();
    }

    private Semaphore semaphore = new Semaphore(3);

    private void test() {
        for (int i = 0; i < 10; i++) {
            new BusinessWindow(i, semaphore).start();
        }
    }

    class BusinessWindow extends Thread {
        private int index;
        private Semaphore semaphore;

        public BusinessWindow(int index, Semaphore semaphore) {
            this.index = index;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            super.run();

            try {
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("index:" + index + ", semaphore:" + semaphore.getQueueLength() + " done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
