package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【搜索二维矩阵】
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-01
 */

public class Demo68 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[][] board = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        int target = sc.nextInt();
        //先找到对应的行数 -> 找到最后一个> xxx 满足条件的
        findMaxTarget(xx,xx);
        //再找到对应的target
        int res = findTarget(xx,xx);

    }


    /**
     * 找到对应的目标值
     * @param nums
     * @param target
     * @return
     */
    public static int findTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找第一个满足条件的值
     * @param nums
     * @param target
     * @return
     */
    public static int findMinTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }


    /**
     * 找最后一个满足条件的值
     * @param nums
     * @param target
     * @return
     */
    public static int findMaxTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

}
