package com.example.argorithmlib.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Copyright (C), 2016-2020
 * FileName: CyclicBarrierTester
 * Author: zhengwei
 * Date: 2020-05-12 21:57
 * Description: CyclicBarrier
 */
public class CyclicBarrierTester {
    private int parties = 3;
    private int result;

    public static void main(String[] args) {
        CyclicBarrierTester tester = new CyclicBarrierTester();
        tester.test();
    }

    public void test() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                System.out.println("At barrier, result:" + result);
            }
        });

        for (int i = 0; i < parties; i++) {
            WorkThread cyclicThread = new WorkThread(cyclicBarrier);
            cyclicThread.start();
        }

        System.out.println("test end, but thread not end");
    }

    class WorkThread extends Thread {
        private CyclicBarrier barrier;

        public WorkThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            super.run();

            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " arrive Barrier, result:" + result);
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
