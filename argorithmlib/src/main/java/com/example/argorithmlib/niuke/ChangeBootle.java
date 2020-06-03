package com.example.argorithmlib.niuke;

import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: ChangeBootle
 * Author: zhengwei
 * Date: 2020-05-31 16:33
 * Description:
 */
public class ChangeBootle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int bottleNum = scanner.nextInt();
            if (bottleNum == 0) {
                break;
            }

            System.out.println(bottleNum / 2);
        }
    }
}
