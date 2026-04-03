package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【寻找峰值】
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *@Author: sonicge
 *@CreateTime: 2026-04-03
 */

public class Demo73 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        //寻找峰值
        int peakElement = findPeakElement(nums);
        System.out.println("峰值的下标为:" + peakElement);
    }

    public static int findPeakElement(int[] nums) {
        //和左边去比较 向上取整法 找最后一个
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
