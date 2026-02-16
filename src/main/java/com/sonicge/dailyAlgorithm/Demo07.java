package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最小跳跃次数II】
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 * 0 <= j <= nums[i] 且
 * i + j < n
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-23
 */
public class Demo07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        int jump = jump(nums);
        System.out.println(jump);

    }

    public static int jump(int[] nums) {
        int size = nums.length;
        if (size == 1) return 0;
        int maxRange = 0;
        int res = 0;
        int cur = 0;
        for (int i = 0; i < size; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange == size - 1) {
                res++;
                return res;
            }
            if (cur == i) {
                cur = maxRange;
                res++;
            }
        }
        return res;
    }
}
