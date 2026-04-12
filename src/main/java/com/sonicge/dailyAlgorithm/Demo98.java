package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【下一个排列】
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 三部曲
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo98 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] =sc.nextInt();
        }
        nextPermutation(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }

    /**
     * 下一个排列
     * 1 4 2 5 3 第一步：找到拐点为2；第二步，拐点和最小的大于它的数交换，1 4 3 5 2 ; 第三步，交换之后后面的要按照从小到大排,1 4 3 2 5
     *  1 4 2 5 3 -> 1 4 3 2 5
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        //1.首先找到拐点
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        //2.拐点和最小的大于它的数交换
        int lastBiggerNum = nums.length - 1;
        if (index >= 0) {
            while (lastBiggerNum >= 0 && nums[lastBiggerNum] <= nums[index]) {
                lastBiggerNum--;
            }
            int temp = nums[index];
            nums[index] = nums[lastBiggerNum];
            nums[lastBiggerNum] = temp;
        }
        //3.将交换点后面的数按照从小到大排序
        int left = index + 1;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

    }
}
