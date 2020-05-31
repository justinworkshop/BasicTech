package com.example.argorithmlib.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: QU
 * Author: zhengwei
 * Date: 2020-05-31 14:43
 * Description:
 */
public class QU {
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] stuArray = new int[n];

            for (int i = 0; i < stuArray.length; i++) {
                stuArray[i] = scanner.nextInt();
            }

            String c;
            int left;
            int right;

            for (int i = 0; i < m; i++) {
                c = scanner.next();
                left = scanner.nextInt();
                right = scanner.nextInt();

                handleQuestion(c, stuArray, left, right);
            }

        }

        scanner.close();

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static void handleQuestion(String c, int[] array, int left, int right) {
        if (c.equals("U")) {
            array[left - 1] = right;
        } else {

            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }

            int maxValue = getMaxFromArray(array, left - 1, right - 1);
            result.add(maxValue);
        }
    }

    private static int getMaxFromArray(int[] array, int left, int right) {
        int[] order = new int[right - left + 1];
        System.arraycopy(array, left, order, 0, right - left + 1);

//        for (int i = 0; i < order.length; i++) {
//            System.out.println(order[i] + " ");
//        }
//
//        System.out.println("-------");

        quickSort(order, 0, order.length - 1);

//        for (int i = 0; i < order.length; i++) {
//            System.out.println(order[i] + " ");
//        }
        return order[order.length - 1];
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int j = right;
        int k = array[left];

        while (i < j) {
            while (i < j && array[j] >= k) {
                j--;
            }

            while (i < j && array[i] <= k) {
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
}
