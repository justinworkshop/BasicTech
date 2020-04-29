package com.example.argorithmlib.t1_dg_and_fz;

/**
 * Copyright (C), 2016-2020
 * FileName: BinaryQuery
 * Author: zhengwei
 * Date: 2020-04-29 15:38
 * Description: 二分查找
 */
public class BinaryQuery {
    public static int binaryQuery(int[] arr, int num) {
        int start = -1;
        int end = arr.length - 1;
        int objIndex = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (num > arr[mid]) {
                start = mid + 1;
            } else if (num < arr[mid]) {
                end = mid - 1;
            } else {
                objIndex = mid;
                break;
            }
        }

        return objIndex;
    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = i;
        }

        int index = binaryQuery(arr, 1);
        System.out.println("index: " + index);
    }
}
