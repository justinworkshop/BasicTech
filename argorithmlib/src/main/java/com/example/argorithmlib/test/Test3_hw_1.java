package com.example.argorithmlib.test;

/**
 * Copyright (C), 2016-2020
 * FileName: Test3_hw_1
 * Author: zhengwei
 * Date: 2020-06-13 21:44
 * Description:
 */
public class Test3_hw_1 {
    public static void main(String[] args) {
        String text = "aaabbaccbababac";

        String maxHuiWen = getMaxHuiWen(text);
        System.out.println(maxHuiWen);

    }

    public static String getMaxHuiWen(String text) {
        String res = "";

        char[] charArray = text.toCharArray();
        int max = 0;

        for (int i = 0; i < charArray.length; i++) {

            int k = i;
            int left = k;
            int right = k;
            boolean sameCharFlag = true;

            while (left >= 0 && right < charArray.length && charArray[left] == charArray[right]) {
                if (charArray[left] != charArray[k]) {
                    sameCharFlag = false;
                }

                left--;
                right++;
            }

            while (sameCharFlag && left >= 0) {
                if (charArray[left] == charArray[k]) {
                    left--;
                } else {
                    break;
                }
            }

            while (sameCharFlag && right < charArray.length) {
                if (charArray[right] == charArray[k]) {
                    right++;
                } else {
                    break;
                }
            }

            left++;
            right--;

            int counter = right - left + 1;

            if (counter > max) {
                max = counter;
                System.out.println("k: " + k + ", left: " + left + ", right: " + right);
                res = text.substring(left, right + 1);
            }

        }

        return res;
    }
}
