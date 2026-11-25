package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 最小花费爬楼梯
 *@Author: sonicge
 *@CreateTime: 2026-11-25
 */

public class Demo132 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length];
        for(int i = 0;i<length;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println("爬楼梯的最小花费为：" + minCostClimbingStairs(arr));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        if (cost.length == 1) {
            return cost[0];
        }
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-2] + cost[i-2],dp[i-1] + cost[i-1]);
        }
        return dp[cost.length];
    }
}
