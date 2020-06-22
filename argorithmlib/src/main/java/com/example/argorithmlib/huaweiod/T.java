package com.example.argorithmlib.huaweiod;

/**
 * Copyright (C), 2016-2020
 * FileName: T
 * Author: zhengwei
 * Date: 2020-06-20 15:57
 * Description:
 */
public class T {

    public static void main(String[] args) {
        String[] array = {"2", "1", "+", "3", "*", "4", "+"};

        int result = calculate(array);

        System.out.println("result: " + result);

    }

    private static int calculate(String[] array) {
        int v1 = 0;
        int v2 = 0;

        for (int i = 0; i < array.length; i++) {

            String s = array[i];
            if (s.equals("+")) {
                v1 = v1 + v2;
            } else if (s.equals("-")) {
                v1 = v1 - v2;
            } else if (s.equals("*")) {
                v1 = v1 * v2;
            } else if (s.equals("/")) {
                v1 = v1 / v2;
            } else {
                int value = Integer.valueOf(s);

                if (i % 2 == 0) {
                    v1 = value;
                } else {
                    v2 = value;
                }
            }
        }

        return v1;
    }
}
