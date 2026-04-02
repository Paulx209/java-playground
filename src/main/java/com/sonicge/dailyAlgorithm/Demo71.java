package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【搜索旋转排序数组】
 *  和上一道题类似，但是这道题不再是寻找对应的最小值了，而是寻找对应的target值
 *@Author: sonicge
 *@CreateTime: 2026-04-02
 */

public class Demo71 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        int res = search(nums, target);
        System.out.println("旋转排序数组中的目标值下标为:" + res);

    }

    public static int search(int[] nums, int target) {
        int size = nums.length;
        //先找到那个0 低台阶中的第一个
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if(nums[left] == target)return left;
        //此时left就是对应的低台阶中的第一个
        int res1 = binarySearch(nums, 0, left - 1, target);
        int res2 = binarySearch(nums, left, size - 1, target);

        return res1 == -1 ? res2 : res1;
    }

    public static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
