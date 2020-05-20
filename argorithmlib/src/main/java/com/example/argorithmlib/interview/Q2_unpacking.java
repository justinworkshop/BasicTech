package com.example.argorithmlib.interview;

/**
 * Copyright (C), 2016-2020
 * FileName: Q2_unpacking
 * Author: zhengwei
 * Date: 2020-05-20 23:38
 * Description: 解压字符串
 */
public class Q2_unpacking {
    public static void main(String[] args) {
        String s = "BHCJSBCSCW[100|DASKDNKJWDNWCNQWCNOQCNQWOICNWQOINCWQOICNQWOIXWOISWIODAOWPQWDMQKOQZCDWF]WQJDWQUINCQQW[99|SDWQJCIQIUWCNQUCNWQIDNWQUIFNSALQP]DQOJOIXZALPPQQAAX";
        String result = unPacking(s);
        System.out.println(result);
    }

    private static String unPacking(String s) {
        final char[] chars = s.toCharArray();

        int leftIndex = 0;
        int gapIndex = 0;
        int rightEndex = 0;
        int kuohaoNum = 0;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'A' && c <= 'Z' && kuohaoNum == 0) {
                stringBuilder.append(c);
            } else if (c >= '0' && c <= '9') {


            } else if (c == '[') {
                if (kuohaoNum == 0) {
                    leftIndex = i;
                }
                kuohaoNum++;
            } else if (c == '|') {
                if (kuohaoNum == 1) {
                    gapIndex = i;
                }

            } else if (c == ']') {
                kuohaoNum--;
                if (kuohaoNum == 0) {
                    rightEndex = i;

//                    System.out.println("left: " + leftIndex + ", gap: " + gapIndex + ", right: " + rightEndex);

                    String value = String.valueOf(chars, leftIndex + 1, gapIndex - (leftIndex + 1));
                    String subString = String.copyValueOf(chars, gapIndex + 1, rightEndex - (gapIndex + 1));
                    int m = Integer.valueOf(value);

//                    System.out.println("m: " + m + ", S: " + subString);
                    for (int j = 0; j < m; j++) {
                        stringBuilder.append(unPacking(subString));
                    }
                }

            }
        }

        return stringBuilder.toString();
    }
}
