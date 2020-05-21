package com.example.argorithmlib.juc;

/**
 * Copyright (C), 2016-2020
 * FileName: DeadLock
 * Author: zhengwei
 * Date: 2020-05-21 17:02
 * Description: 死锁场景
 */
public class DeadLock {
    public static void main(String[] args) {
        LockTest object1 = new LockTest("lock1");
        LockTest object2 = new LockTest("lock2");

        ThreadTest threadA = new ThreadTest("thread A", object1, object2);
        ThreadTest threadB = new ThreadTest("thread B", object2, object1);

        threadA.start();
        threadB.start();

    }

    static class LockTest {
        public String name;

        public LockTest(String name) {
            this.name = name;
        }
    }

    static class ThreadTest extends Thread {
        private LockTest lock1;
        private LockTest lock2;

        public ThreadTest(String name, LockTest lock1, LockTest lock2) {
            this.setName(name);
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start");

            synchronized (lock1) {
                System.out.println(getName() + " get " + lock1.name);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                synchronized (lock2) {
                    System.out.println(getName() + " get " + lock2.name);
                }
            }

            System.out.println(getName() + "  end");
        }
    }

}
