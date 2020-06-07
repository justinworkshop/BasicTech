package com.example.argorithmlib.interview;

/**
 * Copyright (C), 2016-2020
 * FileName: Q3_recycle_array
 * Author: zhengwei
 * Date: 2020-05-21 14:23
 * Description: 循环数组中找数据
 */
public class Q3_recycle_array {
    public static void main(String[] args) {
        int[] array = {50, 52, 63, 64, 65, 68, 88, 90, 98, 3, 8, 15, 44, 45, 46, 49};

        System.out.println("min value: " + findMaxInArray(array, 0, array.length - 1));
    }

    private static int findMinInArray(int[] array, int left, int right) {
        System.out.println("left: " + array[left] + ", right: " + array[right]);

        // 无环
        if (array[left] < array[right]) {
            return array[left];
        }

        if (right - left == 1) {
            return array[left] > array[right] ? array[right] : array[left];
        }

        // 有环
        int mid = (left + right) / 2;
        System.out.println("mid value: " + array[mid]);

        if (array[left] < array[mid]) {
            return findMinInArray(array, mid + 1, right);
        } else {
            return findMinInArray(array, left, mid);
        }
    }

    private static int findMaxInArray(int[] array, int left, int right) {
        System.out.println("left: " + array[left] + ", right: " + array[right]);

        // 无环
        if (array[left] < array[right]) {
            return array[right];
        }

        if (left - right == 1) {
            return array[left] < array[right] ? array[right] : array[left];
        }

        int mid = (left + right) / 2;
        if (array[left] < array[mid]) {
            return findMaxInArray(array, mid, right);
        } else {
            return findMaxInArray(array, left, mid - 1);
        }
    }
}
