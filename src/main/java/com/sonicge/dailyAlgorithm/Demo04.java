package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【轮转数组】
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * @Author: sonicge
 * @CreateTime: 2026-01-19
 */
public class Demo04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        int targetIndex = sc.nextInt();
        targetIndex = targetIndex % size; //可能会出现一种情况 就是size的不够，会转好几圈，所以要取余处理。

        swap(nums,0,size-1);
        swap(nums,0,targetIndex-1);
        swap(nums,targetIndex,size-1);

        for(int num:nums){
            System.out.println(num);
        }

    }
    private static void swap(int[] nums,int left,int right){
        while(left < right){
            int temp=nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
    }
}
