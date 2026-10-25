package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【字符串相乘】 不使用遍历乘法累加 可能会内存溢出
 *@Author: sonicge
 *@CreateTime: 2026-10-25
 */

public class Demo117 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = sc.next();
        String num2 = sc.next();
        System.out.println("累加后的结果为:" + multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int val1 = num1.charAt(i)-'0';
            for (int j = len2 - 1; j >= 0; j--) {
                int val2 = num2.charAt(j)-'0';
                int multi_res = val1 * val2 + res[i+j+1]; // 这里会累加
                res[i+j+1] = multi_res % 10;
                res[i+j] += multi_res / 10; //这里也会累加
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<res.length;i++){
            //sb为空 且 开头为0 因为乘积结果是一个范围 比如说两位数相乘 答案一定是 三位数或者四位数 所以这里数组出现首字为0的情况
            if(sb.isEmpty() && res[i] == 0){
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
