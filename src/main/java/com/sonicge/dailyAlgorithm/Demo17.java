package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 【N字形变换】
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-16
 */

public class Demo17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int numRows = sc.nextInt();
        String res = convert(str, numRows);
        System.out.println("n字形变化之后的结果为：" + res);
    }

    public static String convert(String s, int numRows) {
        if(numRows<2)return s;
        //1.初始化
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        //2.开始遍历
        char[] charArray = s.toCharArray();
        int index = 0;
        int flag = -1;
        //遍历每一个ch
        for (char ch : charArray) {
            list.get(index).append(ch);
            if (index == numRows - 1 || index == 0) {
                flag = -flag;
            }
            index += flag;
        }
        //3.收集所有的StringBuilder
        StringBuilder finalBuilder = new StringBuilder();
        for (StringBuilder builder : list) {
            finalBuilder.append(builder);
        }
        return finalBuilder.toString();
    }

}
