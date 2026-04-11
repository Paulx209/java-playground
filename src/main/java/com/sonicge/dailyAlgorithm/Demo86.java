package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【零钱兑换】
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo86 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] coins = new int[size];
        for (int i = 0; i < size; i++) {
            coins[i] = sc.nextInt();
        }
        int amount = sc.nextInt();
        int coinCount = coinChange(coins, amount);
        System.out.println("最少需要" + coinCount + "个硬币");
    }
    public static  int coinChange(int[] coins, int amount) {
        //dp[amount]:表示总金额凑成amount的最少硬币个数
        int[] dp = new int[amount+1];
        dp[0] = 0;
        Arrays.fill(dp,amount+1);
        for(int i =1;i<amount;i++){
            for(int coin : coins){
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
