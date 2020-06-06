package com.example.argorithmlib.node;

/**
 * Copyright (C), 2016-2020
 * FileName: MyLinkedList
 * Author: zhengwei
 * Date: 2020-06-06 12:04
 * Description:
 */
public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        int n = 5;
        for (int i = 1; i <= n; i++) {
            myLinkedList.addNode(i);
        }
        System.out.println("original LinkedList length: " + myLinkedList.getLength());
        myLinkedList.printLinkedNode();

        myLinkedList.orderList();

        System.out.println("Order seq:");
        myLinkedList.printLinkedNode();

    }

    private Node head;
    private int length;

    static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public void addNode(int d) {
        Node node = new Node(d);

        if (head == null) {
            head = node;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
    }

    public boolean deleteNode(int index) {
        if (index < 1 || index > length) {
            return false;
        }

        if (index == 1) {
            head = head.next;
            return true;
        }

        int i = 2;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }

            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }

        return true;
    }

    public int getLength() {
        int len = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        return len;
    }

    public Node orderList() {
        Node curNode = head;
        Node nextNode;
        int temp;

        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.value < nextNode.value) {
                    temp = curNode.value;
                    curNode.value = nextNode.value;
                    nextNode.value = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }

        return head;
    }

    public void printLinkedNode() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
