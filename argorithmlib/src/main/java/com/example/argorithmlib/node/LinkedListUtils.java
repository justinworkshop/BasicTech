package com.example.argorithmlib.node;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2020
 * FileName: LinkedListUtils
 * Author: zhengwei
 * Date: 2020-06-06 12:36
 * Description:
 */
public class LinkedListUtils {
    public void deleteDuplecate1(Node head) {
        Map<Integer, Integer> map = new HashMap<>();
        Node temp = head;
        Node pre = null;
        while (temp != null) {
            if (map.containsKey(temp.value)) {
                pre.next = temp.next;
            } else {
                map.put(temp.value, 1);
                pre = temp;
            }
            temp = temp.next;
        }
    }

    public void deleteDuplecate2(Node head) {
        Node p = head;
        while (p != null) {
            Node q = p;
            while (q.next != null) {
                if (p.value == q.next.value) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
    }

    /**
     * 找倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        // p2先移动k步
        for (int i = 0; i < k && (p2 != null); i++) {
            p2 = p2.next;
        }

        if (p2 == null) {
            return null;
        }

        // p1、p2同时遍历，p2遍历到尾部时，p1指向第就是倒数第k个节点
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public static Node reverseIteratively(Node head) {
        Node pReverseHead = head;

        Node pNode = head;
        Node pPre = null;

        while (pNode != null) {

            // 1.保存后置节点，因为反转后就断开了，访问不到
            Node pNext = pNode.next;

            // 2.后置节点为null，说明到了链表尾部，反转会尾部节点就是头节点
            if (pNext == null) {
                pReverseHead = pNode;
            }

            // 3.反转
            pNode.next = pPre;

            // 4.指针后移
            pPre = pNode;
            pNode = pNext;
        }

        return pReverseHead;
    }

    public static void printReverse(Node head) {
        if (head != null) {
            printReverse(head.next);
            System.out.println(head.value + " ");
        }

    }

    /**
     * 寻找中间节点
     *
     * @param head
     * @return
     */
    public static Node searchMiddleNode(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        System.out.println("middle node value: " + p1.value);
        return p1;
    }

    /**
     * 判断是否有环
     * @param head
     * @return
     */
    public static boolean checkIsLoop(Node head) {
        Node p1 = head;
        Node p2 = head;
        if (p2 == null) {
            return false;
        }

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            // 指针相等说明链表有环
            if (p1 == p2) {
                return true;
            }
        }

        return false;
    }

}
