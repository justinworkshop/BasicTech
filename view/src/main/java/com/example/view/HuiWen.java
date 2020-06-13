package com.example.view;

/**
 * Copyright (C), 2016-2020
 * FileName: HuiWen
 * Author: zhengwei
 * Date: 2020-06-09 13:01
 * Description: 优化方案，采用一次遍历，同时向左、向右 查找比对
 */
public class HuiWen {
    public static void main(String[] args) {
        String test = "aaabbaccbababac";
        getHuiWen(test);
    }

    public static void getHuiWen(String test) {
        char[] array = test.toCharArray();

        int len = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < array.length && (array.length - i > len); i++) {

            int left = i;
            int right = i;
            int tempLen = 0;

            while (left >= 0 && right < array.length && array[left] == array[right]) {
                left--;
                right++;
                tempLen++;
            }

            if(left >= 0){

            }

            if (tempLen > len) {
                len = tempLen;
                start = left + 1;
                end = right - 1;
            }
        }


        System.out.println("start: " + start + ", end: " + end + ", len: " + len);

        for (int i = start; i <= end; i++) {
            System.out.print(array[i]);
        }

        System.out.println();

    }
}
