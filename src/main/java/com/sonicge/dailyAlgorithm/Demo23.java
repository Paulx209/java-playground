package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【长度最小的子数组】
 * 给定一个数组，长度最小的子数组找出数组中 最小长度的子数组要求满足 元素之和>=target
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-23
 */

public class Demo23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++) {
            nums[i] = sc.nextInt();
        }

        int minLength = minSubArrayLen(target, nums);
        System.out.println("长度最小的子数组的长度为:" + minLength);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        //开始滑动窗口
        int minRes = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int currentSum = 0;
        while(right < nums.length){
            currentSum += nums[right];
            //循环一直找到不满足>=target条件的左边界
            while(currentSum >= target){
                minRes = Math.min(minRes,right-left+1);
                currentSum-=nums[left];
                left++;
            }
            right++;
        }
        return minRes;
    }

}
