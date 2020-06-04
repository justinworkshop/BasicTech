package com.example.argorithmlib.huaweiod;

import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: Q1
 * Author: zhengwei
 * Date: 2020-06-03 21:16
 * Description:
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k, n, m;
        while (scanner.hasNext()) {
            k = scanner.nextInt();
            n = scanner.nextInt();
            m = scanner.nextInt();

            dec2M(k, n, m);
        }
    }

    private static void dec2M(int k, int n, int m) {
        int counter = 0;
        String seq = "";

        if (k > 0 && n >= 0 && n < m) {
            while (k != 0 && k > 0) {
                seq = (k % m) + seq;
                if (k % m == n) {
                    counter++;
                }


                k = k / m;
            }
        }

        System.out.println(seq);
        System.out.println(counter);
    }

//    private static void dec2M(int k, int n, int m) {
//        int counter = 0;
//        String charSeq = "";
//
//        if (k > 0 && n >= 0 && n < m) {
//            while (k != 0 && k > 0) {
//                charSeq += k % m;
//                k = k / m;
//            }
//
//            char[] charArray = charSeq.toCharArray();
//            for (int i = 0; i < charArray.length; i++) {
//                if(charArray[i]==n+'0'){
//                    counter++;
//                }
//            }
//        }
//
//        System.out.println(charSeq + ", " + counter);
//    }

}
