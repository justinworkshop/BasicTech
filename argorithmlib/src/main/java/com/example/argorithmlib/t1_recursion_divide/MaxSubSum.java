package com.example.argorithmlib.t1_recursion_divide;

/**
 * Copyright (C), 2016-2020
 * FileName: MaxSubSum
 * Author: zhengwei
 * Date: 2020-05-17 23:38
 * Description: 最大子段和
 */
public class MaxSubSum {

    static int[] array = {3, -5, 1, 5, -3, 2, 3};

    public static void main(String[] args) {
        getMaxSubSum(array);
    }

    private static void getMaxSubSum(int[] array) {
        int start = 0;
        int end = 0;
        int maxSubSum = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            int tempSum = 0;

            for (int j = i; j < array.length; j++) {
                tempSum = tempSum + array[j];
                if (tempSum > maxSubSum) {
                    start = i;
                    end = j;
                    maxSubSum = tempSum;
                }
            }
        }

        System.out.println("maxSubSum: " + maxSubSum + " start: " + start + ", end: " + end);
    }
}
