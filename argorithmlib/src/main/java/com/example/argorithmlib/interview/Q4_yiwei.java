package com.example.argorithmlib.interview;

/**
 * Copyright (C), 2016-2020
 * FileName: Q4_yiwei
 * Author: zhengwei
 * Date: 2020-05-21 15:49
 * Description: 移位运算
 */
public class Q4_yiwei {
    public static void main(String[] args) {
        System.out.println(isPower(8));
        System.out.println(getCountOne(3));
    }

    /**
     * 判断n是否是2的指数
     *
     * @param n
     * @return
     */
    private static boolean isPower(int n) {
        if (n < 1) {
            return false;
        }

        int i = 1;
        while (i <= n) {
            if (i == n) {
                return true;
            }

            i = i << 1;
        }

        return false;
    }

    /**
     * 计算n中二进制1的个数
     *
     * @param n
     * @return
     */
    private static int getCountOne(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }

        return count;
    }

}
