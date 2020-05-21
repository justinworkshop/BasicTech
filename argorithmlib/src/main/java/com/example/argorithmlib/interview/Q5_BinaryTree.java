package com.example.argorithmlib.interview;

/**
 * Copyright (C), 2016-2020
 * FileName: Q5_BinaryTree
 * Author: zhengwei
 * Date: 2020-05-21 16:32
 * Description: 二叉树
 */
public class Q5_BinaryTree {
    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        int[] data = {2, 8, 7, 4, 9, 3, 1, 6, 7, 5};
        binaryTree.buildTree(data);

        System.out.print("Middle order: ");
        binaryTree.inOrder(binaryTree.root);
        System.out.println();

        System.out.print("Pre order: ");
        binaryTree.preOrder(binaryTree.root);
        System.out.println();

        System.out.print("Post order: ");
        binaryTree.postOrder(binaryTree.root);
    }


    static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        private Node root;

        public BinaryTree() {
            root = null;
        }

        public void insert(int data) {
            Node newNode = new Node(data);
            if (root == null) {
                root = newNode;
            } else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;

                    if (data < current.data) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            return;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            return;
                        }
                    }
                }
            }
        }

        public void buildTree(int[] data) {
            for (int i = 0; i < data.length; i++) {
                insert(data[i]);
            }
        }

        public void inOrder(Node localRoot) {
            if (localRoot != null) {
                inOrder(localRoot.left);
                System.out.print(localRoot.data + " ");
                inOrder(localRoot.right);
            }
        }

        public void preOrder(Node localRoot) {
            if (localRoot != null) {
                System.out.print(localRoot.data + " ");
                preOrder(localRoot.left);
                preOrder(localRoot.right);
            }
        }

        public void postOrder(Node localRoot) {
            if (localRoot != null) {
                postOrder(localRoot.left);
                postOrder(localRoot.right);
                System.out.print(localRoot.data + " ");
            }
        }


    }
}
