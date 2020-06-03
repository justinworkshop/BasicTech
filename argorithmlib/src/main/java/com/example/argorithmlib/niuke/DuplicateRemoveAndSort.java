package com.example.argorithmlib.niuke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: DuplicateRemoveAndSort
 * Author: zhengwei
 * Date: 2020-05-31 16:40
 * Description:
 */
public class DuplicateRemoveAndSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int value;
        while (scanner.hasNext()) {
            int size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                value = scanner.nextInt();
                if (!list.contains(value))
                    list.add(value);
            }

            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer t0, Integer t1) {
                    return t0.intValue() - t1.intValue();
                }
            });

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            list.clear();
        }

        scanner.close();
    }
}
