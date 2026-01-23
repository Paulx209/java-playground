package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * @Author: sonicge
 * @CreateTime: 2026-01-20
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Demo05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        //由于只能一天买入 一天卖出 双指针
        int left = 0;
        int right = size - 1;
        int res = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            min=Math.min(min,nums[left++]);
            max=Math.max(max,nums[right--]);
            res = Math.max(res, (max-min));
        }
        System.out.println(res);
    }
}
