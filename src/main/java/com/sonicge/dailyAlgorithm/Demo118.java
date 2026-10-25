package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【字符串转换整数 (atoi)】
 *@Author: sonicge
 *@CreateTime: 2026-10-25
 */

public class Demo118 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (!"exit".equals(str)) {
            str = sc.next();
            System.out.println("最终的结果为:" + myAtoi(str));
        }
    }

    public static int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int size = s.length();
        int index = 0;
        boolean flag = true;
        //遇到空格就跳过
        while (index < size && s.charAt(index) == ' ') {
            index++;
        }
        if (index < size && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            if (s.charAt(index) == '-') {
                flag = !flag;
            }
            index++;
        }
        //只有数字才处理
        int res = 0;
        while (index < size && Character.isDigit(s.charAt(index))) {
            int val = s.charAt(index) - '0';
            //判断是否会超标
            if ((Integer.MAX_VALUE - val) / 10 < res) {
                return !flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + val;
            index++;
        }
        if (!flag) {
            res *= -1;
        }
        return res;

    }
}
