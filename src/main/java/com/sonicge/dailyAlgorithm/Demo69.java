package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【在排序数组中查找元素的第一个和最后一个位置】
 *  两次二分法即可搞定！
 *
 *  给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *  如果数组中不存在目标值 target，返回 [-1, -1]。
 *  你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 *  输入：nums = [5,7,7,8,8,10], target = 8
 *  输出：[3,4]
 *@Author: sonicge
 *@CreateTime: 2026-04-02
 */

public class Demo69 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = sc.nextInt();
        }

        int target = sc.nextInt();
        int[] res= searchRange(nums,target);
        for(int num : res){
            System.out.println(num);
        }

    }
    public static int[] searchRange(int[] nums,int target){
        if(nums == null || nums.length == 0)return new int[]{-1,-1};
        //先找到满足条件的第一个元素下标
        int[] res = new int[2];
        int length = nums.length;
        int left = 0;
        int right = length -1 ;
        while(left < right){
            int mid = left  + (right - left) /2 ;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        if(nums[left] != target){
            return new int[]{-1,-1};
        }
        res[0] = left;
        //找到满足条件的第二个元素下标
        left = 0;
        right = length -1 ;
        while(left < right){
            int mid = left  + (right - left +1 ) /2 ;
            if(nums[mid] <= target){
                left = mid;
            }else{
                right = mid-1;
            }
        }
        res[1] = left;
        return res;
    }
}
