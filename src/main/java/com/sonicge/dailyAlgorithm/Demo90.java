package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【乘积最大子数组】
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo90 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        int res = maxProduct(nums);
        System.out.println("最大子数组为:" + res);
    }

    public static int maxProduct(int[] nums) {
        int size = nums.length;
        int[][] dp = new int[size][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = dp[0][0];
        for (int i = 1; i < size; i++) {
            if (nums[i] > 0) {
                dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
                max = Math.max(max, dp[i][0]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                max = Math.max(max, dp[i][0]);
            }
        }
        return max;
    }
}
