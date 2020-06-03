package com.example.argorithmlib.niuke;

import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: Hex2Dec
 * Author: zhengwei
 * Date: 2020-05-31 17:15
 * Description:
 */
public class Hex2Dec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s;
        while (scanner.hasNext()) {
            s = scanner.next();
            int dexValue = getDexValue(s, 16);
            System.out.println(String.valueOf(dexValue));
        }

        scanner.close();
    }

    private static int getDexValue(String s, int format) {
        int x = s.indexOf("x");
        char[] array = s.substring(x + 1).toCharArray();

        int d;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            d = array[array.length - i - 1];
            if (d >= '0' && d <= '9') {
                d = d - '0';
            } else if (d >= 'a' && d <= 'z') {
                d = d - 'a' + 10;
            } else if (d >= 'A' && d <= 'Z') {
                d = d - 'A' + 10;
            }

            result = result + ((int) (Math.pow(format, i))) * d;
        }

        return result;
    }
}
