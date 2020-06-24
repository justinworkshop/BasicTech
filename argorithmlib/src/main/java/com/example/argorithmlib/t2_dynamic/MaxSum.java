package com.example.argorithmlib.t2_dynamic;

/**
 * Copyright (C), 2016-2020
 * FileName: MaxSum
 * Author: zhengwei
 * Date: 2020-06-24 13:28
 * Description: 最大子段和
 */
public class MaxSum {
    public static void main(String[] args) {
        int[] array = {-2, 11, -4, 13, -5, -2};
        getMaxSum(array);
    }

    public static int getMaxSum(int[] array) {
        int sum = 0;
        int temp = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < array.length; i++) {
            if (temp > 0) {
                temp += array[i];
            } else {
                start = i;
                temp = array[i];
            }

            if (temp > sum) {
                end = i;
                sum = temp;
            }
        }

        System.out.println("start: " + start + ", end: " + end + ", sum: " + sum);

        return sum;
    }
}
