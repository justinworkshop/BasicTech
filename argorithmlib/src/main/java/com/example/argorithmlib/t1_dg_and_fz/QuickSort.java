package com.example.argorithmlib.t1_dg_and_fz;

/**
 * Copyright (C), 2016-2020
 * FileName: QuickSort
 * Author: zhengwei
 * Date: 2020-04-29 14:54
 * Description: 快排, 基于递归与分治
 */
public class QuickSort {

    /**
     * 方法1
     *
     * @param array
     * @param left
     * @param right
     */
    public void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int j = right;
        int k = array[left];

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
            }
        }

        array[left] = array[i];
        array[i] = k;

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    /**
     * 方法2
     *
     * @param array
     * @param left
     * @param right
     */
    public void quickSort2(int[] array, int left, int right) {
        int i = getCenterPosition(array, left, right);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    private int getCenterPosition(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int k = array[left];

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
            }
        }

        array[left] = array[i];
        array[i] = k;

        return i;
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 7, 2, 4, 9, 12};

        QuickSort sort = new QuickSort();
        sort.quickSort2(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
    }
}
