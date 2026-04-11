package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【分割等和子集】
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo91 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        boolean b = canPartition(nums);
        System.out.println("是否可以分割成等和子集:" + b);
    }

    public static boolean canPartition(int[] nums) {
        //剪枝处理
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        //开始dp
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        //01背包问题 先遍历物品 再遍历容量
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) dp[j] = true;
            }
        }
        return dp[target];
    }
}
