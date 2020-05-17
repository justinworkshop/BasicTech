package com.example.argorithmlib.t3_greedy;

/**
 * Copyright (C), 2016-2020
 * FileName: GreedyLoad
 * Author: zhengwei
 * Date: 2020-05-18 00:05
 * Description: 贪心算法求解最有装载，体积不限的情况下，装载最多集装箱
 */
public class GreedyLoad {
    static int[] weight = {1, 3, 15, 2, 8, 4, 9, 6};
    static int carryWeight = 30;

    public static void main(String[] args) {
        int[] result = greedyLoad(carryWeight, weight);

        printArray(weight);
        printArray(result);
    }

    private static int[] greedyLoad(int c, int[] w) {
        int[] result = new int[w.length];
        int[] index = new int[w.length];

        for (int i = 0; i < w.length; i++) {
            index[i] = i;
        }
        printArray(index);

        int[] orderIndex = quickSort(weight, 0, weight.length - 1, index);
        printArray(orderIndex);

        for (int i = 0; i < w.length && w[orderIndex[i]] <= c; i++) {
            result[orderIndex[i]] = 1;
            c = c - w[orderIndex[i]];
        }

        return result;
    }

    private static int[] quickSort(int[] array, int left, int right, int[] index) {
        if (left > right) {
            return index;
        }

        int i = left;
        int j = right;
        int k = array[left];
        int indexK = index[left];

        while (i < j) {
            while (array[j] >= k && i < j) {
                j--;
            }

            while (array[i] <= k && i < j) {
                i++;
            }

            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                temp = index[i];
                index[i] = index[j];
                index[j] = temp;
            }
        }

        array[left] = array[i];
        array[i] = k;

        index[left] = index[i];
        index[i] = indexK;

        quickSort(array, left, i - 1, index);
        quickSort(array, i + 1, right, index);

        return index;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }
}
