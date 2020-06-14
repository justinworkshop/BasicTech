package com.example.argorithmlib.test;

/**
 * Copyright (C), 2016-2020
 * FileName: Test3_hw_2
 * Author: zhengwei
 * Date: 2020-06-09 10:38
 * Description:要求：不参考资料/API实现，不超过1小时
 * 算法实现：在大文本中查找最长的回文串（该字符串反序之后同原字符串一样），并返回（如果没有返回Null）【测试用例：aaabbaccbababac，返回babab】
 */
public class Test3_hw_2 {
    public static void main(String[] args) {
        String content = "aaabbaccbababac";
        getHuiWenString(content);

    }

    public static void getHuiWenString(String str) {
        char[] array = str.toCharArray();
        char[] arrayRev = getReverseArray(array);

        print(array);
        print(arrayRev);

        int maxLength = 0;
        int tempLen = 0;
        int start = 0;
        int end = 0;
        int flagStart = 0;
        int flagEnd = 0;


        int j = 0;
        // 优化点 array.length - i > maxLength  添加条件，减少遍历
        for (int i = 0; i < array.length && (array.length - i > maxLength); i++) {

            // 找到开始位置, 优化点 array.length - j > maxLength  添加条件，减少遍历
            for (j = 0; j < arrayRev.length && (array.length - j > maxLength); j++) {
                if (array[i] == arrayRev[j]) {
                    flagStart = j;
                }

                while (true) {
                    if (array[i] == arrayRev[j] && i < array.length && j < array.length) {
                        tempLen++;
                        i++;
                        j++;
                    } else {

                        flagEnd = j;

                        if (tempLen > maxLength) {
                            maxLength = tempLen;
                            start = flagStart;
                            end = flagEnd;
                        }

                        tempLen = 0;

                        break;
                    }
                }

            }


        }

        for (int i = start; i < end; i++) {
            System.out.print(arrayRev[i] + " ");
        }

        System.out.println();
    }

    public static char[] getReverseArray(char[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[array.length - i - 1];
        }

        return result;
    }

    public static void print(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
