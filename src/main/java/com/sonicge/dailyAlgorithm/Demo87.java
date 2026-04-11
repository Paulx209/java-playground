package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【完全平方数】
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 和零钱兑换是同一个思路，有哪几种零钱呢？ 1 -> Math.sqrt(n)
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo87 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = numSquares(n);
        System.out.println(n+"数字最少可由" + res + "个完全平方数组成");
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        //先遍历空间 再遍历物品
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(n); j++) {
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }
        return dp[n];
    }
}
