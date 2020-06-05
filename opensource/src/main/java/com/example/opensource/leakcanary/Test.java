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
        Test test = new Test();
        test.testLeak();
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
}
