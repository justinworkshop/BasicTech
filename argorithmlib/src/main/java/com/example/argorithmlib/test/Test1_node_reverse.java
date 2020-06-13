package com.example.argorithmlib.test;

/**
 * Copyright (C), 2016-2020
 * FileName: Test1_node_reverse
 * Author: zhengwei
 * Date: 2020-06-13 20:29
 * Description:
 */
public class Test1_node_reverse {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        Node root = buildLinkedNode(array);
        printLinkedNode(root);

        int midIndex = getMidPosition(root);
        System.out.println("midIndex: " + midIndex);

//        Node reverseHead = reverseLinkedNode(root);
//        printLinkedNode(reverseHead);

        Node reverseByParams = reverseByParams(root, midIndex);
        printLinkedNode(reverseByParams);
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int getMidPosition(Node head) {
        Node fastNode = head;
        int index = 0;

        while (head != null && fastNode != null && fastNode.next != null) {
            head = head.next;
            fastNode = fastNode.next.next;
            index++;
        }

        return index;
    }

    public static Node reverseByParams(Node head, int position) {
        Node tempNode = head;
        Node node = head;

        position--;
        while (node != null && position > 0) {
            node = node.next;
            position--;
        }

        Node midHead = reverseLinkedNode(node.next);
        node.next = midHead;

//        printLinkedNode(midHead);

        return tempNode;
    }

    public static Node reverseLinkedNode(Node head) {
        Node reverseHead = null;

        Node curNode = head;
        Node preNode = null;
        Node nextNode = null;

        while (curNode != null) {

            // step 1
            nextNode = curNode.next;

            // step 2
            if (nextNode == null) {
                reverseHead = curNode;
            }

            // step 3
            curNode.next = preNode;

            // step 4
            preNode = curNode;
            curNode = nextNode;

        }

        return reverseHead;
    }

    public static Node buildLinkedNode(int[] array) {
        Node head = null;
        Node node = null;
        Node newNode = null;

        for (int i = 0; i < array.length; i++) {
            newNode = new Node(array[i]);

            if (head == null) {
                head = newNode;
                node = head;
            } else {
                node.next = newNode;
                node = node.next;
            }
        }

        return head;
    }

    public static void printLinkedNode(Node node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
