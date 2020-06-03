package com.example.argorithmlib.niuke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright (C), 2016-2020
 * FileName: StaticsLog
 * Author: zhengwei
 * Date: 2020-05-31 15:50
 * Description:
 */
public class StaticsLog {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();//使用LinkedHashMap而非hashmap

        String filePath;
        int numPoint;

        String key;
        String fileName;

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            filePath = in.next();
            numPoint = in.nextInt();

            int id = filePath.lastIndexOf('\\');
            fileName = id < 0 ? filePath : filePath.substring(id + 1);

            key = fileName + " " + numPoint;

            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        in.close();

        // 对记录进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> arg0, Map.Entry<String, Integer> arg1) {
                return (arg1.getValue() - arg0.getValue()) == 0 ? (arg0.getValue() - arg1.getValue()) : (arg1.getValue() - arg0.getValue());
            }
        });

        // 只输出前8条
        int m = 0;
        for (Map.Entry<String, Integer> mapping : list) {
            m++;
            if (m <= 8) {
                String[] str = mapping.getKey().split(" ");//str[0]:zxcvbnmzxc.c  str[1]:23
                // split(String regex) 根据给定正则表达式的匹配拆分此字符串。
                String k = str[0].length() > 16 ? str[0].substring(str[0].length() - 16) : str[0];
                String n = str[1];
                System.out.println(k + " " + n + " " + mapping.getValue());
            } else {
                break;
            }
        }


    }
}
