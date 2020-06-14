package com.example.argorithmlib.interview;

/**
 * Copyright (C), 2016-2020
 * FileName: Q1_merge_node
 * Author: zhengwei
 * Date: 2020-05-20 21:30
 * Description: 合并链表
 */
public class Q1_merge_node {
    public static void main(String[] args) {

        LinkNode[] nodeArray1 = {new LinkNode(1), new LinkNode(3), new LinkNode(5), new LinkNode(7)};
        LinkNode[] nodeArray2 = {new LinkNode(2), new LinkNode(4)};
        LinkNode node1 = createLinkNode(nodeArray1);
        LinkNode node2 = createLinkNode(nodeArray2);

        printLinkNode(node1);
        printLinkNode(node2);


        printLinkNode(mergeLinkNode(node1, node2));

    }

    public static class LinkNode {
        int val;
        LinkNode next;

        public LinkNode(int val) {
            this.val = val;
        }

    }

    private static LinkNode mergeLinkNode(LinkNode a, LinkNode b) {

        LinkNode head = new LinkNode(0);
        LinkNode cur = head;

        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                cur = cur.next;
                a = a.next;
            } else {
                cur.next = b;
                cur = cur.next;
                b = b.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (a == null) {
            cur.next = b;
        } else {
            cur.next = a;
        }
        return head.next;
    }

    private static LinkNode createLinkNode(LinkNode[] nodes) {
        if (nodes == null || nodes.length < 1) {
            return null;
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        return nodes[0];
    }

    private static void printLinkNode(LinkNode node) {
        while (node != null) {
            System.out.print(node.val + " --> ");
            node = node.next;
        }

        System.out.println("null");
    }
}
