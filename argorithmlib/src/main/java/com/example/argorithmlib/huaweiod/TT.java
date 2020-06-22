package com.example.argorithmlib.huaweiod;

/**
 * Copyright (C), 2016-2020
 * FileName: TT
 * Author: zhengwei
 * Date: 2020-06-20 16:32
 * Description:
 */
public class TT {

    public static void main(String[] args) {
        int n = 5;
        int[][] bookingArray = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};

        int[] result = getBookingForAirplane(bookingArray, n);

        printArray(result);
    }

    public static int[] getBookingForAirplane(int[][] bookingArray, int n) {
        int[] result = new int[n];

        for (int i = 0; i < bookingArray.length; i++) {
            int[] temp = bookingArray[i];

            for (int j = temp[0] - 1; j <= temp[1] - 1; j++) {
                result[j] += temp[2];
            }
        }

        return result;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

