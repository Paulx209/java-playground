package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。
 * 然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。
 * @Author: sonicge
 * @CreateTime: 2026-01-20
 */

public class Demo05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        int maxProfit = 0;
        for(int i=0;i<size-1;i++){
            maxProfit += (nums[i+1] > nums[i] ? nums[i+1] - nums[i] : 0);
        }
        System.out.println(maxProfit);
    }
}
