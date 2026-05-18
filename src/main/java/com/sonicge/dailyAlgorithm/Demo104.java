package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【x的平方根】
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 *@Author: sonicge
 *@CreateTime: 2026-05-18
 */

public class Demo104 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        int res = mySqrt(target);
        System.out.println(target + "的值的平方根为："+res);
    }
    public static int mySqrt(int x){
        int left = 1;
        int right = x;
        while (left < right){
            int mid = left + (right - left ) /2;
            if(mid < x / mid)left = mid+1;
            else if(mid > x / mid)right = mid-1;
            else return mid;
        }
        long multi = (long)left * left;
        if(multi > x)return left-1;
        return left;
    }
}
