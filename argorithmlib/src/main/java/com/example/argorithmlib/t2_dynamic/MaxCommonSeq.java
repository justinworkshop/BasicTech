package com.example.argorithmlib.t2_dynamic;

/**
 * Copyright (C), 2016-2020
 * FileName: MaxCommonSeq
 * Author: zhengwei
 * Date: 2020-06-24 11:36
 * Description: 最长公共子序列
 */
public class MaxCommonSeq {

    /**
     * X = {x1,x2...xi}
     * Y = {y1,y2...yj}
     * c[][] 用于计算最长公共子序列的长度
     *
     * @param args
     */
    public static void main(String[] args) {
        char[] x = {'x', 'A', 'B', 'C', 'B', 'D', 'A', 'B', 'x'};
        char[] y = {'y', 'B', 'D', 'C', 'A', 'B', 'A', 'y'};
        int[][] c = new int[x.length][y.length];
        int[][] b = new int[x.length][y.length];

        lcsLength(x.length - 1, y.length - 1, x, y, c, b);

        printArray(c);
        printArray(b);

        lcs(x.length - 1, y.length - 1, x, b);

    }

    public static void lcsLength(int m, int n, char[] x, char[] y, int[][] c, int[][] b) {
        int i, j;

        for (i = 1; i <= m; i++) {
            c[i][0] = 0;
        }

        for (j = 1; j <= n; j++) {
            c[0][j] = 0;
        }

        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }
            }
        }
    }

    public static void lcs(int i, int j, char[] x, int[][] b) {
        if (i == 0 || j == 0) {
            return;
        }

        if (b[i][j] == 1) {
            lcs(i - 1, j - 1, x, b);
            System.out.print(x[i] + " ");
        } else if (b[i][j] == 2) {
            lcs(i - 1, j, x, b);
        } else {
            lcs(i, j - 1, x, b);
        }
    }

    public static void printArray(int[][] array) {
        System.out.println(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
