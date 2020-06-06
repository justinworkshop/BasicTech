package com.example.argorithmlib.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2020
 * FileName: T
 * Author: zhengwei
 * Date: 2020-06-04 20:03
 * Description:
 * <p>
 * 逆序单链表的后半部，例如1 2 3 4 操作之后编程 1 2 4 3
 */
public class T {

    public static void main(String[] args) {
        T t = new T();
        Node node = t.build();
        node = t.printNode(node);
        node = t.reverseHalf(node);
        t.printNode(node);

    }

    public static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node reverseHalf(Node node) {
        List<Node> list = new ArrayList<>();

        Node root = node;
        Node headNode = node;
        Node tempNode = node;

        int center = 0;

        while (node != null) {
            node = node.next;

            if (tempNode.next != null) {
                tempNode = tempNode.next.next;
                center++;
            } else {
                if (node != null) {
                    list.add(node);
                }
            }
        }

        System.out.println("center position: " + center + ", list.size: " + list.size());

        for (int i = 0; i < center - 1; i++) {
            headNode = headNode.next;
        }

        for (int i = list.size(); i > 0; i--) {
            System.out.println(list.get(i-1).value);
            headNode.next = list.get(i - 1);
            headNode = headNode.next;
        }

        headNode.next = null;

        return root;
    }

    public Node build() {
        int n = 8;

        Node head = null;
        Node point = null;
        for (int i = 1; i < n; i++) {
            Node node = new Node(i);
            if (head == null) {
                head = node;
                point = head;
            } else {
                point.next = node;
                point = point.next;
            }

        }

        return head;
    }

    public Node printNode(Node node) {
        Node head = node;
        while (node != null) {
            System.out.print(" --> " + node.value);
            node = node.next;
        }

        System.out.println();

        return head;
    }

    public int getLength(Node node) {
        int len = 0;
        while (node.next != null) {
            node = node.next;
            len++;
        }

        return len;
    }


}
