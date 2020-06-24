package com.example.opensource.leakcanary;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Copyright (C), 2016-2020
 * FileName: Test
 * Author: zhengwei
 * Date: 2020-06-05 12:58
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        try {
            test1();
            test2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testLeak() {
//        AppWatcher.INSTANCE.getObjectWatcher().watch(object, "object");

        Object object = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        // 弱引用配合引用队列
        WeakReference weakReference = new WeakReference(object, referenceQueue);
        System.out.println("weakReference: " + weakReference);

        System.out.println("==================");

        object = null; // 释放对象，让object弱可达，会进入引用队列。这样在ReferenceQueue中可以遍历到，说明就不会发生内存泄漏

        Runtime.getRuntime().gc();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Reference ref;
        do {
            ref = referenceQueue.poll();
            System.out.println(ref + " in queue");
        } while (ref != null);

    }

    private static void test1() throws InterruptedException {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        Object object = new Object();
        WeakReference weakReference = new WeakReference(object, referenceQueue);

        System.out.println("Before GC, ref: " + weakReference.get());
        System.out.println("Before GC, queue: " + referenceQueue.poll());

        Runtime.getRuntime().gc();
        Thread.sleep(300);

        System.out.println("-----");

        System.out.println("After GC, ref: " + weakReference.get());
        System.out.println("After GC, queue: " + referenceQueue.poll());
    }

    private static void test2() throws InterruptedException {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        WeakReference weakReference = new WeakReference(new Object(), referenceQueue);

        System.out.println("Before GC, ref: " + weakReference.get());
        System.out.println("Before GC, queue: " + referenceQueue.poll());

        Runtime.getRuntime().gc();
        Thread.sleep(300);

        System.out.println("-----");

        System.out.println("After GC, ref: " + weakReference.get());
        System.out.println("After GC, queue: " + referenceQueue.poll());
    }

    private static void printRefQueue(ReferenceQueue queue) {
        Reference reference;
        while (queue != null) {
            reference = queue.poll();
            if (reference != null) {
                System.out.println(reference);
            } else {
                break;
            }
        }
    }
}
