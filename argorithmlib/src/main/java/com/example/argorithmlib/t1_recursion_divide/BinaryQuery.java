package com.example.argorithmlib.t1_recursion_divide;

import java.lang.reflect.Array;

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
        int objIndex = -1;

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

    public static int[] binaryInsert(int[] arr, int value) {
        int start = -1;
        int end = arr.length - 1;
        int objIndex = -1;
        int mid = -1;


        if (value < arr[0]) {
            mid = 0;
        } else if (value > arr[arr.length - 1]) {
            mid = arr.length;
        } else {
            while (start <= end) {
                mid = (start + end) / 2;
                if (value > arr[mid]) {
                    start = mid + 1;
                } else if (value < arr[mid]) {
                    end = mid - 1;
                } else {
                    objIndex = mid;
                    break;
                }
            }

        }


        System.out.println("objIndex: " + objIndex + ", mid: " + mid);

        if (objIndex != -1) {
            arr[objIndex] = value;
            return arr;
        } else {
            int newArray[] = (int[]) Array.newInstance(arr.getClass().getComponentType(), arr.length + 1);
            System.arraycopy(arr, 0, newArray, 0, mid);
            newArray[mid] = value;
            System.arraycopy(arr, mid, newArray, mid + 1, arr.length - mid);
            return newArray;
        }
    }

    public static int[] buildArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 5;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        System.out.print("size: " + arr.length + ", array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = buildArray();
        printArray(arr);

//        int index = binaryQuery(arr, 6);
//        System.out.println("index: " + index);

        int[] result = binaryInsert(arr, 31);
        printArray(result);
    }
}
