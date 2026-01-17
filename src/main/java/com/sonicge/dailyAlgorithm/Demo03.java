package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-17
 */
public class Demo03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }
        // 1 1 1 2 2 3
        if(nums.length < 2 ){
            return ;
        }
        int slow = 2;
        int fast = 2;
        while(fast < size){
            if(nums[fast] != nums[slow-2]){
                nums[slow] = nums[fast];
                slow ++;
            }
            fast++;
        }
    }
}
