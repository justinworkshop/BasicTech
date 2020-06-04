package com.example.argorithmlib.huaweiod;

import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: Q2
 * Author: zhengwei
 * Date: 2020-06-03 21:55
 * Description:
 */
public class Q2 {
    public static void main(String[] args) {


        int N = 50;
        Q2 q2 = new Q2();
        int[] table = q2.buildTable(N);

        Scanner scanner = new Scanner(System.in);
        int step = 0;
        while (scanner.hasNext()){
            step = scanner.nextInt();
            q2.queryTable(table,step);
        }

    }

    private int[] buildTable(int N) {
        int value[] = new int[N + 1];
        for (int i = 0; i < value.length; i++) {
            if (i <= 2) {
                value[i] = 1;
            } else {
                value[i] = value[i - 3] + value[i - 1];
            }
        }

        return value;
    }

    private void queryTable(int[] table, int step) {
        if (step < table.length) {
            System.out.println(table[step]);
        }
    }


}
