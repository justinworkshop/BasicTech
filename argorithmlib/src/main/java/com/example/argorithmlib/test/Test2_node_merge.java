package com.example.argorithmlib.test;

/**
 * Copyright (C), 2016-2020
 * FileName: Test2_node_merge
 * Author: zhengwei
 * Date: 2020-06-07 18:57
 * Description:
 */
public class Test2_node_merge {
    public static void main(String[] args) {
        int[] a = {1, 3, 6};
        int[] b = {4, 6, 7, 8};

        Node nodeA = buildLinkedNode(a);
        Node nodeB = buildLinkedNode(b);

        printLinkedNode(nodeA);
        printLinkedNode(nodeB);

        Node resultNode = mergeNode(nodeA, nodeB);
        printLinkedNode(resultNode);
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node mergeNode(Node nodeA, Node nodeB) {
        Node head = null;
        if (nodeA.value <= nodeB.value) {
            head = nodeA;
        } else {
            head = nodeB;
        }
        Node headTemp = head;

        while (nodeA != null && nodeB != null) {
            Node temp;

            if (nodeA.value <= nodeB.value) {
                temp = nodeA;
                nodeA = nodeA.next;
            } else {
                temp = nodeB;
                nodeB = nodeB.next;
            }

            head.next = temp;
            head = head.next;
        }

        if (nodeA != null) {
            head.next = nodeA;
        }

        if (nodeB != null) {
            head.next = nodeB;
        }

        return headTemp;
    }

    public static Node buildLinkedNode(int[] arr) {
        Node head = null;
        Node headTemp = null;

        for (int i = 0; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            if (head == null) {
                head = newNode;
                headTemp = head;
            } else {
                head.next = newNode;
                head = head.next;
            }
        }

        return headTemp;
    }

    public static void printLinkedNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
