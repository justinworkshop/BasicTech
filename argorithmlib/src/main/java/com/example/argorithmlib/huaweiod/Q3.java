package com.example.argorithmlib.huaweiod;

import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: Q3
 * Author: zhengwei
 * Date: 2020-06-03 23:05
 * Description:
 */
public class Q3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String content;
        int value;

        while (scanner.hasNext()) {
            content = scanner.next();
            value = printMaxSubContentLen(content);
            System.out.println(value);
        }
    }

    private static int printMaxSubContentLen(String content) {
        int maxSubLen = -1;
        char[] chars = content.toCharArray();

        int start = 0;
        int end = 0;
        int alphaBetCounter = 0;
        int numCounter = 0;

        for (int i = 0; i < chars.length; i++) {
            start = i;
            if (isAlphabet(chars[i])) {
                alphaBetCounter++;
            } else {
                numCounter++;
            }

            for (int j = i + 1; j < chars.length; j++) {
                end = j;
                if (isAlphabet(chars[j])) {
                    alphaBetCounter++;
                } else {
                    numCounter++;
                }

                if (alphaBetCounter > 1) {
                    end = j - 1;
                    break;
                }
            }

            if (numCounter > 0 && (alphaBetCounter == 2 || alphaBetCounter == 1)) {
                int temp = end - start + 1;
                if (temp > maxSubLen) {
                    maxSubLen = temp;
                }
            }

            alphaBetCounter = 0;
            numCounter = 0;
        }


        return maxSubLen;
    }

    private static boolean isAlphabet(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;
    }


}
